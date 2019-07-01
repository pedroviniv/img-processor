/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.util.List;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Layer {
    
    private String content;
    private List<Filter> filters;
    private Position position;
    private Boolean behind = false;

    public Layer(String content, List<Filter> filters, Position position, Boolean behind) {
        this.content = content;
        this.filters = filters;
        this.position = position;
        this.behind = behind;
    }

    public Layer() {
    }

    public String getContent() {
        return content;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Position getPosition() {
        return position;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getBehind() {
        return behind;
    }

    public void setBehind(Boolean behind) {
        if (behind == null) {
            return;
        }
        this.behind = behind;
    }

    @Override
    public String toString() {
        return "Layer{" + "content=" + content + ", filters=" + filters + ", position=" + position + ", behind=" + behind + '}';
    }
}
