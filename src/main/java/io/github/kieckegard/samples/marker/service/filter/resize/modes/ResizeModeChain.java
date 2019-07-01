/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.resize.modes;

import io.github.kieckegard.samples.marker.service.filter.Filter;
import io.github.kieckegard.samples.marker.service.filter.FilterContext;
import io.github.kieckegard.samples.marker.service.common.GenericChain;
import io.github.kieckegard.samples.marker.service.filter.resize.ResizeFilter;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public abstract class ResizeModeChain extends GenericChain<FilterContext> {
    
    private final String resizeMode;

    public ResizeModeChain(String resizeMode) {
        this.resizeMode = resizeMode;
    }

    @Override
    protected abstract void handle(FilterContext context);

    @Override
    protected boolean shouldHandle(FilterContext context) {
        
        final ResizeFilter resizeFilter = (ResizeFilter) context.getFilter();
        
        return resizeFilter.getResizeMode().equals(resizeMode);
    }
    
}
