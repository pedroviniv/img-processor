/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.function.Function;
import javax.imageio.ImageIO;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class LayerLoader implements Function<Layer, ProcessedLayer> {
    
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

    @Override
    public ProcessedLayer apply(Layer layer) {
        return this.load(layer);
    }
    
}
