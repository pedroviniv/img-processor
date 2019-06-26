/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 * @param <Filter>
 */
public abstract class FilterChain extends GenericChain<FilterContext> {
    
    private final String type;

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
