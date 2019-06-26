/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.resize;

import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.GenericChain;

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
        return context.getFilter().getResizeMode().equals(resizeMode);
    }
    
}
