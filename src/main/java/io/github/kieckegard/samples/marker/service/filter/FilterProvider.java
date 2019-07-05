/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

import io.github.kieckegard.samples.marker.service.filter.crop.CropFilterChain;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModeChain;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModes;
import io.github.kieckegard.samples.marker.service.filter.resize.ResizeFilterChain;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModeChain;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */

@Configuration
public class FilterProvider {
    
    public static final String DEFAULT_FILTER = "DEFAULT_FILTER";
    
    @Bean(FilterTypes.CROP)
    public FilterChain cropModesChain(@Qualifier(CropModes.CENTERED) final CropModeChain cropModesChain) {
        
        final CropFilterChain cropFilterChain = new CropFilterChain(cropModesChain);
        return cropFilterChain;
    }
    
    @Bean(FilterTypes.RESIZE)
    public FilterChain resizeModesChain(@Qualifier(ResizeModes.COVER) final ResizeModeChain resizeModeChain) {
        
        final ResizeFilterChain resizeFilterChain = new ResizeFilterChain(resizeModeChain);
        return resizeFilterChain;
    }
    
    @Bean(FilterProvider.DEFAULT_FILTER)
    public FilterChain provideFilterChain(
            @Qualifier(FilterTypes.CROP) FilterChain cropFilterChain, 
            @Qualifier(FilterTypes.RESIZE) FilterChain resizeFilterChain) {
        
        cropFilterChain.setNext(resizeFilterChain);
        
        return cropFilterChain;
    }
}
