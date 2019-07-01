/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.resize.modes.cover;

import io.github.kieckegard.samples.marker.service.filter.BoundingBox;
import io.github.kieckegard.samples.marker.service.filter.FilterContext;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModeChain;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModes;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.imgscalr.Scalr;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CoverResizeModeChain extends ResizeModeChain {

    public CoverResizeModeChain() {
        super(ResizeModes.COVER);
    }
    
    /**
     * Redimensiona a {@link BufferedImage} diminuindo ou aumentando seu tamanho de maneira que ela consiga cobrir
     * o {@link DrawableRectangle} passado.
     * 
     * @param sourceImage
     * @param toBeCovered
     * @return
     * @throws IOException 
     * 
     * [Deve ser exposto num serviço que sera injetado nesta chain]
     */
    private BufferedImage resizeCover(BufferedImage sourceImage, BoundingBox toBeCovered) throws IOException {
        
        Dimension imageDimension = new Dimension(sourceImage.getWidth(), sourceImage.getHeight());
        
        Scalr.Mode fitMode = Scalr.Mode.FIT_TO_WIDTH;
        
        if (imageDimension.getWidth() > imageDimension.getHeight()) {
            fitMode = Scalr.Mode.FIT_TO_HEIGHT;
        }

        BufferedImage resized = Scalr.resize(sourceImage, fitMode, toBeCovered.getWidth());
        
        return resized;
    }

    @Override
    protected void handle(FilterContext context) {
        
        final CoverResizeFilter coverResizeFilter = (CoverResizeFilter) context.getFilter();
        
        Cover cover = coverResizeFilter.getCover();
        BufferedImage image = context.getImage();
        
        System.out.println("resizing cover image... ");
        try {
            BufferedImage resizedImage = this.resizeCover(image, cover.getBoundingBox());
            context.setImage(resizedImage);
            
        } catch (IOException ex) {
            Logger.getLogger(CoverResizeModeChain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
