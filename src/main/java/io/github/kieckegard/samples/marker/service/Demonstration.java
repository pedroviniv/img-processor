/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.crop.centered.CenteredCropFilterChain;
import io.github.kieckegard.samples.marker.service.resize.ResizeModes;
import io.github.kieckegard.samples.marker.service.resize.cover.Cover;
import io.github.kieckegard.samples.marker.BoundingBox;
import io.github.kieckegard.samples.marker.service.crop.CropFilterChain;
import io.github.kieckegard.samples.marker.service.resize.ResizeFilterChain;
import io.github.kieckegard.samples.marker.service.resize.cover.CoverResizeModeChain;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Demonstration {
    
    private static FilterChain buildChain() {
        
        CenteredCropFilterChain centeredCutFilter = new CenteredCropFilterChain();
        
        CropFilterChain cutFilter = new CropFilterChain(centeredCutFilter);
        
        CoverResizeModeChain coverResizeModeChain = new CoverResizeModeChain();
        ResizeFilterChain resizeFilter = new ResizeFilterChain(coverResizeModeChain);
        
        cutFilter.setNext(resizeFilter);
        
        return cutFilter;
    }
    
    private static FilterContext context(Filter filter, String content) {
        return null;
    }
    
    public static void main(String[] args) {
        
        FilterChain filterChain = buildChain();
        
        List<Layer> layers = new ArrayList<>();
        
        layers.forEach(l -> {
            
            List<Filter> filters = l.getFilters();
            
            filters.stream()
                    .map(f -> {
                        return context(f, l.getContent());
                    })
                    .reduce((accumulated, next) -> {
                
                        filterChain.chain(next);
                        return next;
                    });
            
        });
    }
}
