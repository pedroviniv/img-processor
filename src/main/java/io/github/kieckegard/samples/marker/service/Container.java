/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.gfx.BufferedImageConverter;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Container {
    
    private final Graphics2D graphics2DContainer;
    private final BufferedImage containerContent;

    private Container(Graphics2D graphics2D, BufferedImage containerContent) {
        this.graphics2DContainer = graphics2D;
        this.containerContent = containerContent;
    }

    public static class Builder {
        private BufferedImage containerContent;

        private Builder() {
        }

        public Builder containerContent(final BufferedImage value) {
            final BufferedImage container = new BufferedImage(value.getWidth(), value.getHeight(), value.getType());
            this.containerContent = new BufferedImageConverter().apply(container);
            return this;
        }
        
        private Graphics2D create(BufferedImage container) {
            
            Graphics2D graphics2D = container.createGraphics();
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            graphics2D.fillRect(0, 0, 256, 256);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            
            return graphics2D;
        }

        public Container build() {
            Graphics2D graphics2D = this.create(this.containerContent);
            return new io.github.kieckegard.samples.marker.service.Container(graphics2D, containerContent);
        }
    }

    public static Container.Builder builder() {
        return new Container.Builder();
    }

    public Graphics2D getGraphics2DContainer() {
        return graphics2DContainer;
    }

    public BufferedImage getContainerContent() {
        return containerContent;
    }
    
    public void draw(BufferedImage content, Position position) {
        this.graphics2DContainer.drawImage(content, null, position.getDistanceToTheLeft(), position.getDistanceToTheTop());
    }
    
    /**
     * Ends the drawing "session". 
     * 
     * After calling this method, you won't be able to draw in this container.
     * Be sure you call only after drawing everything you need.
     */
    public void endDrawing() {
        this.graphics2DContainer.dispose();
    }

    @Override
    public String toString() {
        return "Container{" + "graphics2DContainer=" + graphics2DContainer + ", containerContent=" + containerContent + '}';
    }
}
