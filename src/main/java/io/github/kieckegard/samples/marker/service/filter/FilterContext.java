/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class FilterContext {
    
    private BufferedImage result;
    private Filter filter;

    public FilterContext(BufferedImage result, Filter filter) {
        this.result = result;
        this.filter = filter;
    }

    public static class Builder {

        private BufferedImage result;
        private Filter filter;

        private Builder() {
        }

        public Builder result(final BufferedImage value) {
            this.result = value;
            return this;
        }

        public Builder filter(final Filter value) {
            this.filter = value;
            return this;
        }

        public FilterContext build() {
            return new io.github.kieckegard.samples.marker.service.filter.FilterContext(result, filter);
        }
    }

    public static FilterContext.Builder builder() {
        return new FilterContext.Builder();
    }

    public BufferedImage getImage() {
        return result;
    }

    public Filter getFilter() {
        return filter;
    }
    
    public void setImage(final BufferedImage image) {
        this.result = image;
    }
}
