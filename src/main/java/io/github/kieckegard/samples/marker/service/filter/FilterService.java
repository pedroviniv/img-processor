/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

import io.github.kieckegard.samples.marker.service.ProcessedLayer;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class FilterService {
    
    private final FilterChain filterChain;

    public FilterService(final FilterChain filterChain) {
        this.filterChain = filterChain;
    }
    
    private FilterContext toFilterContext(final BufferedImage content, Filter filter) {
        return FilterContext.builder()
                .filter(filter)
                .result(content)
                .build();
    }

    public BufferedImage handle(final ProcessedLayer processedLayer) {
        
        BufferedImage content = processedLayer.getLoadedImage();
        List<Filter> filters = processedLayer.getLayer().getFilters();
        
        if (filters == null || filters.isEmpty()) {
            return content;
        }
        
        else {
            
            List<FilterContext> filterContexts = filters.stream().map(filter -> {
                return this.toFilterContext(content, filter);
            }).collect(Collectors.toList());
            
            FilterContext firstFilterContext = filterContexts.get(0);
            
            this.filterChain.chain(firstFilterContext);
            
            Optional<FilterContext> finalFilterContext = filterContexts.stream()
                    .reduce((accumulated, next) -> {
                        
                        BufferedImage processedImage = accumulated.getImage();
                        next.setImage(processedImage);
                        
                        this.filterChain.chain(next);
                        
                        return next;
                    });
            
            return finalFilterContext.get().getImage();
        }
    }
}
