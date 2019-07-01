/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.awt.image.BufferedImage;

/**
 * Representa uma camada processada, ou seja, com o conteudo
 * da url ja baixado e carregado no atributo loadedImage.
 * 
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class ProcessedLayer {
    
    private BufferedImage loadedImage;
    private Layer layer;

    public ProcessedLayer(BufferedImage loadedImage, Layer layer) {
        this.loadedImage = loadedImage;
        this.layer = layer;
    }

    public static class Builder {

        private BufferedImage loadedImage;
        private Layer layer;

        private Builder() {
        }

        public Builder loadedImage(final BufferedImage value) {
            this.loadedImage = value;
            return this;
        }

        public Builder layer(final Layer value) {
            this.layer = value;
            return this;
        }

        public ProcessedLayer build() {
            return new io.github.kieckegard.samples.marker.service.ProcessedLayer(loadedImage, layer);
        }
    }

    public static ProcessedLayer.Builder builder() {
        return new ProcessedLayer.Builder();
    }

    protected ProcessedLayer() {}
    
    public BufferedImage getLoadedImage() {
        return loadedImage;
    }

    public void setLoadedImage(BufferedImage loadedImage) {
        this.loadedImage = loadedImage;
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "ProcessedLayer{" + "loadedImage=" + loadedImage + ", layer=" + layer + '}';
    }
}
