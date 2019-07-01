/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class BoundingBox {
    
    private Integer width;
    private Integer height;

    public static class Builder {

        private Integer width;
        private Integer height;

        private Builder() {
        }

        public Builder width(final Integer value) {
            this.width = value;
            return this;
        }

        public Builder height(final Integer value) {
            this.height = value;
            return this;
        }

        public BoundingBox build() {
            return new io.github.kieckegard.samples.marker.service.filter.BoundingBox(width, height);
        }
    }

    public static BoundingBox.Builder builder() {
        return new BoundingBox.Builder();
    }

    private BoundingBox(final Integer width, final Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "BoundingBox{" + "width=" + width + ", height=" + height + '}';
    }
}
