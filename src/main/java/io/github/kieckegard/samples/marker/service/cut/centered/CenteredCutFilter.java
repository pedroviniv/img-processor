/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.cut.centered;

import io.github.kieckegard.samples.marker.BoundingBox;
import io.github.kieckegard.samples.marker.TopLeftCorner;
import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.cut.Cut;
import io.github.kieckegard.samples.marker.service.cut.CutFilter;
import io.github.kieckegard.samples.marker.service.cut.CutModeChain;
import io.github.kieckegard.samples.marker.service.cut.CutModes;
import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CenteredCutFilter extends CutModeChain {

    public CenteredCutFilter() {
        super(CutModes.CENTERED);
    }
    
    /**
     * Calcula as coordenadas x,y do canto superior esquerdo da {@link BufferedImage}
     * passada de maneira que um retangulo das dimensoes passadas em {@link DrawableRectangle}
     * seja possivel ser desenhado de maneira centralizada.
     * @param targetImage
     * @param bounding
     * @return 
     */
    private TopLeftCorner getCenteredTopLeftCorner(BufferedImage targetImage, BoundingBox bounding) {
        
        Integer imageWidth = targetImage.getWidth();
        Integer imageHeight = targetImage.getHeight();
        
        Integer x = 0;
        Integer y = 0;
        
        if (!bounding.getWidth().equals(imageWidth)) {
            Integer halfWidth = imageWidth / 2;
            Integer halfSquareWidth = bounding.getWidth() / 2;
            x = halfWidth - halfSquareWidth;
        }
        
        if (!bounding.getHeight().equals(imageHeight)) {
            
            Integer halfHeight = imageHeight / 2;
            Integer halfSquareHeight = bounding.getHeight() / 2;
            
            y = halfHeight - halfSquareHeight;
        }
        
        return TopLeftCorner.builder().x(x).y(y).build();
    }
    
    /**
     * Corta da {@link BufferedImage} passada de maneira centralizada, o conteudo nas dimensoes definidas
     * no {@link DrawableRectangle} passado.
     * @param sourceImage
     * @param toBeCropped
     * @return 
     */
    private BufferedImage cropCenter(BufferedImage sourceImage, BoundingBox toBeCropped) {
        
        TopLeftCorner topLeftToCut = getCenteredTopLeftCorner(sourceImage, toBeCropped);
        
        BufferedImage cropped = Scalr.crop(sourceImage, topLeftToCut.getX(), topLeftToCut.getY(), 
                toBeCropped.getWidth(), toBeCropped.getHeight());
        
        return cropped;
    }

    @Override
    protected void handle(FilterContext context) {
        
        BufferedImage imageToBeCropped = context.getImage();
        Cut cut = context.getFilter().getCut();
        BoundingBox toBeCropped = cut.getToBeCropped();
        
        BufferedImage croppedImage = this.cropCenter(imageToBeCropped, toBeCropped);
        context.setImage(croppedImage);
    }
    
}
