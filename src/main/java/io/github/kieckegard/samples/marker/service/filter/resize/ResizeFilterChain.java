/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.resize;

import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModeChain;
import io.github.kieckegard.samples.marker.service.filter.FilterChain;
import io.github.kieckegard.samples.marker.service.filter.FilterContext;
import io.github.kieckegard.samples.marker.service.filter.FilterTypes;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */

@Component(FilterTypes.RESIZE)
public class ResizeFilterChain extends FilterChain {
    
    private ResizeModeChain resizeModeChain;

    public ResizeFilterChain(@Qualifier(ResizeModes.COVER) final ResizeModeChain resizeModeChain) {
        super(FilterTypes.RESIZE);
        this.resizeModeChain = resizeModeChain;
    }

    @Override
    protected void handle(FilterContext context) {
        
        // chama chain de ResizeModes
        this.resizeModeChain.chain(context);
    }
    
}
