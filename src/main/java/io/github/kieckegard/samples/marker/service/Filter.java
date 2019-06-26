/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.cut.Cut;
import io.github.kieckegard.samples.marker.service.resize.cover.Cover;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Filter {
    
    private String type;
    
    private String resizeMode;
    private Cover cover;
    
    private String cutMode;
    private Cut cut;

    public String getType() {
        return type;
    }

    public String getResizeMode() {
        return resizeMode;
    }

    public Cover getCover() {
        return cover;
    }

    public String getCutMode() {
        return cutMode;
    }

    public Cut getCut() {
        return cut;
    }
}
