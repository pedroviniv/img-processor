/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.cut;

import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.GenericChain;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public abstract class CutModeChain extends GenericChain<FilterContext> {
    
    private final String cutMode;

    public CutModeChain(String cutMode) {
        this.cutMode = cutMode;
    }
    
    @Override
    protected boolean shouldHandle(FilterContext context) {
        return context.getFilter().getCutMode().equals(cutMode);
    }
    
}
