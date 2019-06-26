/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.cut.centered.CenteredCutFilter;
import io.github.kieckegard.samples.marker.service.resize.ResizeModes;
import io.github.kieckegard.samples.marker.service.resize.cover.Cover;
import io.github.kieckegard.samples.marker.BoundingBox;
import io.github.kieckegard.samples.marker.service.cut.CutFilter;
import io.github.kieckegard.samples.marker.service.resize.ResizeFilter;
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
        
        CenteredCutFilter centeredCutFilter = new CenteredCutFilter();
        
        CutFilter cutFilter = new CutFilter(centeredCutFilter);
        
        CoverResizeModeChain coverResizeModeChain = new CoverResizeModeChain();
        ResizeFilter resizeFilter = new ResizeFilter(coverResizeModeChain);
        
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
