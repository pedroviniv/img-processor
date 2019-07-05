/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.crop;

import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModeChain;
import io.github.kieckegard.samples.marker.service.filter.FilterChain;
import io.github.kieckegard.samples.marker.service.filter.FilterContext;
import io.github.kieckegard.samples.marker.service.filter.FilterTypes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
@Component(FilterTypes.CROP)
public class CropFilterChain extends FilterChain {
    
    private final CropModeChain cropModeChain;

    public CropFilterChain(@Qualifier() final CropModeChain cropModeChain) {
        super(FilterTypes.CROP);
        this.cropModeChain = cropModeChain;
    }

    @Override
    protected void handle(FilterContext context) {
        this.cropModeChain.chain(context);
    }
}
