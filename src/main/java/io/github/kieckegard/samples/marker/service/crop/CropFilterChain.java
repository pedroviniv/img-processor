/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.crop;

import io.github.kieckegard.samples.marker.service.crop.modes.CropModeChain;
import io.github.kieckegard.samples.marker.service.FilterChain;
import io.github.kieckegard.samples.marker.service.FilterContext;
import io.github.kieckegard.samples.marker.service.FilterTypes;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class CropFilterChain extends FilterChain {
    
    private final CropModeChain cropModeChain;

    public CropFilterChain(final CropModeChain cropModeChain) {
        super(FilterTypes.CROP);
        this.cropModeChain = cropModeChain;
    }

    @Override
    protected void handle(FilterContext context) {
        this.cropModeChain.chain(context);
    }
}
