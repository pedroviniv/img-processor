/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class FilterContext {
    
    private BufferedImage result;
    private Filter filter;

    public BufferedImage getImage() {
        return result;
    }

    public Filter getFilter() {
        return filter;
    }
    
    public void setImage(final BufferedImage image) {
        this.result = image;
    }
}
