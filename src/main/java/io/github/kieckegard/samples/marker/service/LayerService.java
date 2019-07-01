/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    private static BufferedImage load(final String url) throws IOException {
        URL urlInstance = new URL(url);
        BufferedImage loadedContent = ImageIO.read(urlInstance);
        return loadedContent;
    }

    private ProcessedLayer load(final Layer layer) {

        try {
            String contentUrl = layer.getContent();
            BufferedImage content = load(contentUrl);
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
        
        testSave("first-to-merge", first.getLoadedImage());
        testSave("second-to-merge", second.getLoadedImage());

        Container container = this.createContainer(first.getLoadedImage());

        if (secondLayer.getBehind()) {

            container.draw(second.getLoadedImage(), secondLayer.getPosition());
            
            testSave("second-with-first-size", container.getContainerContent());
            
            container.draw(first.getLoadedImage(), new Position(0, 0));
        } else {
            container.draw(first.getLoadedImage(), new Position(0, 0));
            container.draw(second.getLoadedImage(), secondLayer.getPosition());
        }
        
        testSave("merged", container.getContainerContent());

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

    public void process(List<Layer> layers) throws IOException {

        List<ProcessedLayer> processedLayers = layers.stream()
                .map(this::load)
                .collect(Collectors.toList());

        if (processedLayers.isEmpty()) {
            return;
        }

        final ProcessedLayer processedLayer = processedLayers.get(0);
        final BufferedImage firstLayerImage = this.filterService.handle(processedLayer);

        // just for testing purpose
        this.testSave(this.randomFileName(), firstLayerImage);

        processedLayer.setLoadedImage(firstLayerImage);

        Optional<ProcessedLayer> processedLayersResult = processedLayers.stream()
                .reduce((accumulated, next) -> {

                    final BufferedImage prevProcessedImage = accumulated.getLoadedImage();
                    final BufferedImage nextProcessedImage = this.filterService.handle(next);
                    next.setLoadedImage(nextProcessedImage);
                    
                    // just for testing purpose
                    this.testSave(this.randomFileName(), nextProcessedImage);
                    
                    this.merge(accumulated, next);

                    return next;
                });

        BufferedImage finalResult = processedLayersResult.get().getLoadedImage();
        ImageIO.write(finalResult, "PNG", new File("final-result.png"));
    }
}
