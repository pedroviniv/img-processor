/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.resize.ResizeModes;
import io.github.kieckegard.samples.marker.service.resize.cover.Cover;

/**
 *
 * @author kieckegard
 */
public class CoverResizeFilter extends ResizeFilter {
    
    private Cover cover;
    
    public CoverResizeFilter(final Cover cover) {
        super(ResizeModes.COVER);
        this.cover = cover;
    }

    public Cover getCover() {
        return cover;
    }

    @Deprecated
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "CoverResizeFilter{" + "cover=" + cover + '}';
    }
}
