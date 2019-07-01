/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.util.List;

/**
 * Representa uma requisiçao de um client, que passa uma lista de layers,
 * cada uma com seus filtros, posiçao, conteudo, etc.
 * @author kieckegard
 */
public class Request {
    
    private List<Layer> layers;
    private String contentName;

    public Request(final List<Layer> layers, final String contentName) {
        this.layers = layers;
        this.contentName = contentName;
    }

    public Request() {}

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @Override
    public String toString() {
        return "Request{" + "layers=" + layers + ", contentName=" + contentName + '}';
    }
}
