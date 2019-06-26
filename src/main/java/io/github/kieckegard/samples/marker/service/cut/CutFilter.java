/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.cut;

import io.github.kieckegard.samples.marker.service.FilterChain;
import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.FilterTypes;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CutFilter extends FilterChain {
    
    private final CutModeChain cutModeChain;

    public CutFilter(final CutModeChain cutModeChain) {
        super(FilterTypes.CUT);
        this.cutModeChain = cutModeChain;
    }

    @Override
    protected void handle(FilterContext context) {
        this.cutModeChain.chain(context);
    }
}
