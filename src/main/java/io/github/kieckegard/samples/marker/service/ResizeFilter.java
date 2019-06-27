/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

/**
 *
 * @author kieckegard
 */
public class ResizeFilter extends Filter {
    
    private String resizeMode;

    public ResizeFilter(String resizeMode) {
        super(FilterTypes.RESIZE);
        this.resizeMode = resizeMode;
    }

    public ResizeFilter() {
    }

    public String getResizeMode() {
        return resizeMode;
    }

    public void setResizeMode(String resizeMode) {
        this.resizeMode = resizeMode;
    }

    @Override
    public String toString() {
        return "ResizeFilter{" + "resizeMode=" + resizeMode + '}';
    }
}
