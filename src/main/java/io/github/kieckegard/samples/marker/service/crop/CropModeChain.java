/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.crop;

import io.github.kieckegard.samples.marker.service.CropFilter;
import io.github.kieckegard.samples.marker.service.Filter;
import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.GenericChain;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public abstract class CropModeChain extends GenericChain<FilterContext> {
    
    private final String cropMode;

    public CropModeChain(String cropMode) {
        this.cropMode = cropMode;
    }
    
    @Override
    protected boolean shouldHandle(FilterContext context) {
        
         CropFilter cropFilter = (CropFilter) context.getFilter();
        
        return cropFilter.getCropMode().equals(cropMode);
    }
    
}
