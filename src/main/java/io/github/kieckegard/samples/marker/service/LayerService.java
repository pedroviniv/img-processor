/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.filter.FilterService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class LayerService {

    private final FilterService filterService;

    public LayerService(FilterService filterService) {
        this.filterService = filterService;
    }

    private BufferedImage load(final String url) throws IOException {
        URL urlInstance = new URL(url);
        BufferedImage loadedContent = ImageIO.read(urlInstance);
        return loadedContent;
    }

    /**
     * Carrega o conteudo de uma camada.
     * @param layer
     * @return 
     */
    private ProcessedLayer load(final Layer layer) {

        try {
            String contentUrl = layer.getContent();
            BufferedImage content = this.load(contentUrl);
            return ProcessedLayer.builder()
                    .layer(layer)
                    .loadedImage(content)
                    .build();
        } catch (IOException ex) {
            throw new LayerLoadingException(layer.getContent(), ex);
        }
    }

    public Container createContainer(BufferedImage img) {

        return Container.builder()
                .containerContent(img)
                .build();
    }

    /**
     * mescla a camada first com a camada second. caso a segunda camada esteja
     * atras da primeira, um container com o size da primeira camada e criado, o
     * conteudo da segunda imagem e desenhado e em seguida, o conteudo da
     * primeira camada eh desenhado em cima.
     *
     * caso nao esteja atras, o container com o size da primeira eh criado
     * normalmente, o conteudo da primeira camada eh desenhada na posicao 0,0 e
     * sem seguida, o conteudo da segunda eh desenhado em cima.
     *
     * @param first
     * @param second
     */
    public void merge(ProcessedLayer first, ProcessedLayer second) {

        Layer secondLayer = second.getLayer();
        
        // testSave("first-to-merge", first.getLoadedImage());
        // testSave("second-to-merge", second.getLoadedImage());

        Container container = this.createContainer(first.getLoadedImage());

        if (secondLayer.getBehind()) {

            container.draw(second.getLoadedImage(), secondLayer.getPosition());
            
            // testSave("second-with-first-size", container.getContainerContent());
            
            container.draw(first.getLoadedImage(), new Position(0, 0));
        } else {
            container.draw(first.getLoadedImage(), new Position(0, 0));
            container.draw(second.getLoadedImage(), secondLayer.getPosition());
        }
        
        // testSave("merged", container.getContainerContent());

        second.setLoadedImage(container.getContainerContent());
    }
    
    private String randomFileName() {
        return UUID.randomUUID().toString() + "-layer";
    }

    private void testSave(String fileName, BufferedImage img) {
        try {
            ImageIO.write(img, "PNG", new File(fileName + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(LayerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Processa, de maneira assincrona, o conjunto de layers dentro da requisiçao passada.
     * @param request estrutura de dados contendo as camadas, seus filtros, etc a ser processado.
     * @return
     * @throws LayerLoadingException caso haja algum problema durante o carregamento do conteudo da camada.
     * @throws EmptyLayersException  caso nao tenha sido passado quaisquer camadas para processamento.
     */
    public CompletableFuture<Response> handle(Request request) throws LayerLoadingException, EmptyLayersException {
        return CompletableFuture.supplyAsync(() -> {
           return this.process(request); 
        });
    }

    private Response process(Request request) throws LayerLoadingException, EmptyLayersException {
        
        final List<Layer> layers = request.getLayers();

        if (layers == null || layers.isEmpty()) {
            throw new EmptyLayersException("Voce precisa passar pelo menos uma layer.");
        }
        
        List<ProcessedLayer> processedLayers = layers.stream()
                .map(this::load)
                .collect(Collectors.toList());

        final ProcessedLayer processedLayer = processedLayers.get(0);
        final BufferedImage firstLayerImage = this.filterService.handle(processedLayer);
        processedLayer.setLoadedImage(firstLayerImage);

        // just for testing purpose
        // this.testSave(this.randomFileName(), firstLayerImage);

        Optional<ProcessedLayer> processedLayersResult = processedLayers.stream()
                .reduce((accumulated, next) -> {

                    final BufferedImage prevProcessedImage = accumulated.getLoadedImage();
                    final BufferedImage nextProcessedImage = this.filterService.handle(next);
                    next.setLoadedImage(nextProcessedImage);
                    
                    // just for testing purpose
                    // this.testSave(this.randomFileName(), nextProcessedImage);
                    
                    this.merge(accumulated, next);

                    return next;
                });

        BufferedImage finalResult = processedLayersResult.get().getLoadedImage();
        
        return Response.builder()
                .contentName(request.getContentName())
                .content(finalResult)
                .build();
    }
}
