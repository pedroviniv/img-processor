/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.gfx.BufferedImageConverter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.function.Function;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LayerLoader implements Function<Layer, ProcessedLayer> {
    
    private final BufferedImageConverter converter;
    
    @Autowired
    public LayerLoader(final BufferedImageConverter converter) {
        this.converter = converter;
    }
    
    private BufferedImage load(final String url) throws IOException {
        
        URL urlInstance = new URL(url);
        BufferedImage loadedContent = ImageIO.read(urlInstance);
        
        return this.converter.apply(loadedContent);
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
