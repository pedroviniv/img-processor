/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.util.List;

/**
 *
 * @author kieckegard
 */
public class Request {
    
    private List<Layer> layers;

    public Request(List<Layer> layers) {
        this.layers = layers;
    }

    public Request() {
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    @Override
    public String toString() {
        return "Request{" + "layers=" + layers + '}';
    }
}
