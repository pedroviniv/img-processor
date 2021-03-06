/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

import io.github.kieckegard.samples.marker.service.common.GenericChain;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 * @param <Filter>
 */
public abstract class FilterChain extends GenericChain<FilterContext> {
    
    private final String type;
    
    public static final String RESIZE_IMPL = "RESIZE_IMPL";
    public static final String CROP_IMPL = "CROP_IMPL";

    public FilterChain(String type) {
        this.type = type;
    }

    @Override
    protected abstract void handle(FilterContext context);

    @Override
    protected boolean shouldHandle(FilterContext context) {
        return type.equals(context.getFilter().getType());
    }
    
}
