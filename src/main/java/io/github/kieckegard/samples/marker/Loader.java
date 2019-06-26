/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Loader {
    
    /**
     * Calcula as coordenadas x,y do canto superior esquerdo da {@link BufferedImage}
     * passada de maneira que um retangulo das dimensoes passadas em {@link DrawableRectangle}
     * seja possivel ser desenhado de maneira centralizada.
     * @param targetImage
     * @param bounding
     * @return 
     */
    private static TopLeftCorner getCenteredTopLeftCorner(BufferedImage targetImage, BoundingBox bounding) {
        
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
    private static BufferedImage cropCenter(BufferedImage sourceImage, BoundingBox toBeCropped) {
        
        TopLeftCorner topLeftToCut = getCenteredTopLeftCorner(sourceImage, toBeCropped);
        
        BufferedImage cropped = Scalr.crop(sourceImage, topLeftToCut.getX(), topLeftToCut.getY(), 
                toBeCropped.getWidth(), toBeCropped.getHeight());
        
        return cropped;
    }
    
    /**
     * Redimensiona a {@link BufferedImage} diminuindo ou aumentando seu tamanho de maneira que ela consiga cobrir
     * o {@link DrawableRectangle} passado.
     * 
     * @param sourceImage
     * @param toBeCovered
     * @return
     * @throws IOException 
     */
    private static BufferedImage resizeCover(BufferedImage sourceImage, BoundingBox toBeCovered) throws IOException {
        
        Dimension imageDimension = new Dimension(sourceImage.getWidth(), sourceImage.getHeight());
        
        Mode fitMode = Mode.FIT_TO_WIDTH;
        
        if (imageDimension.getWidth() > imageDimension.getHeight()) {
            fitMode = Mode.FIT_TO_HEIGHT;
        }

        BufferedImage resized = Scalr.resize(sourceImage, fitMode, toBeCovered.getWidth());
        
        return resized;
    }
    
    public static void main(String[] args) { 
        
        BoundingBox drawableRectangle = BoundingBox.builder()
                .width(388)
                .height(224)
                .build();
        
        MarkerProperties marker = MarkerProperties.builder()
                .drawableRectangle(drawableRectangle)
                .distanceToTheTop(65)
                .distanceToTheLeft(65)
                .build();
        
        try {
            
            BufferedImage image = ImageIO.read(new File(Images.PROFILE));
            
            BufferedImage transparentMarker = ImageIO.read(new File(Images.MARKER));
            
            BufferedImage resized = resizeCover(image, drawableRectangle);
            BufferedImage cropped = cropCenter(resized, drawableRectangle);
            
            
            RenderingHints rendering = new RenderingHints(
             RenderingHints.KEY_RENDERING,
             RenderingHints.VALUE_RENDER_QUALITY);
            rendering.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            
            // creating container to start the drawning
            BufferedImage markerContainer = new BufferedImage(transparentMarker.getWidth(), transparentMarker.getHeight(), transparentMarker.getType());
            Graphics2D markerEmptyContainerGraphics = markerContainer.createGraphics();
            markerEmptyContainerGraphics.setRenderingHints(rendering);
            markerEmptyContainerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            markerEmptyContainerGraphics.fillRect(0,0,256,256);
            markerEmptyContainerGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            
            // desenhando o conteudo do marcador na imagem cortada exatamente na posiçao onde ele deve estar
            markerEmptyContainerGraphics.drawImage(cropped, null, marker.getDistanceToTheLeft(), marker.getDistanceToTheTop());
            // desenhando o marcador por cima de tudo. Como o local onde ficara o conteudo e transparente, o conteudo
            // ficara visivel ainda que esteja atras.
            markerEmptyContainerGraphics.drawImage(transparentMarker, null, 0, 0);
            
            ImageIO.write(markerContainer, "PNG", new File("modifiedMarker.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
