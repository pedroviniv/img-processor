/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.resize;

import io.github.kieckegard.samples.marker.service.FilterChain;
import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.FilterTypes;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class ResizeFilter extends FilterChain {
    
    private ResizeModeChain resizeModeChain;

    public ResizeFilter(final ResizeModeChain resizeModeChain) {
        super(FilterTypes.RESIZE);
        this.resizeModeChain = resizeModeChain;
    }

    @Override
    protected void handle(FilterContext context) {
        
        // chama chain de ResizeModes
        this.resizeModeChain.handle(context);
    }
    
}
