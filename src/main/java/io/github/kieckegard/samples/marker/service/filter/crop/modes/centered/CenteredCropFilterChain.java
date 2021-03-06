/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.crop.modes.centered;

import io.github.kieckegard.samples.marker.service.filter.BoundingBox;
import io.github.kieckegard.samples.marker.service.Position;
import io.github.kieckegard.samples.marker.service.filter.FilterContext;
import io.github.kieckegard.samples.marker.service.filter.crop.Crop;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModeChain;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModes;
import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */

@Component(CropModes.CENTERED)
public class CenteredCropFilterChain extends CropModeChain {

    public CenteredCropFilterChain() {
        super(CropModes.CENTERED);
    }
    
    /**
     * Calcula as coordenadas x,y do canto superior esquerdo da {@link BufferedImage}
     * passada de maneira que um retangulo das dimensoes passadas em {@link DrawableRectangle}
     * seja possivel ser desenhado de maneira centralizada.
     * @param targetImage
     * @param bounding
     * @return 
     */
    private Position getCenteredTopLeftCorner(BufferedImage targetImage, BoundingBox bounding) {
        
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
        
        return Position.builder().distanceToTheLeft(x).distanceToTheTop(y).build();
    }
    
    /**
     * Corta da {@link BufferedImage} passada de maneira centralizada, o conteudo nas dimensoes definidas
     * no {@link DrawableRectangle} passado.
     * @param sourceImage
     * @param toBeCropped
     * @return 
     */
    private BufferedImage cropCenter(BufferedImage sourceImage, BoundingBox toBeCropped) {
        
        Position topLeftToCut = getCenteredTopLeftCorner(sourceImage, toBeCropped);
        
        BufferedImage cropped = Scalr.crop(sourceImage, topLeftToCut.getDistanceToTheLeft(), topLeftToCut.getDistanceToTheTop(), 
                toBeCropped.getWidth(), toBeCropped.getHeight());
        
        return cropped;
    }

    @Override
    protected void handle(FilterContext context) {
        
        final CenteredCropFilter centeredCropFilter = 
                (CenteredCropFilter) context.getFilter();
        
        System.out.println("cropping centering image... ");
        BufferedImage imageToBeCropped = context.getImage();
        Crop crop = centeredCropFilter.getCrop();
        BoundingBox toBeCropped = crop.getToBeCropped();
        
        BufferedImage croppedImage = this.cropCenter(imageToBeCropped, toBeCropped);
        context.setImage(croppedImage);
    }
    
}
