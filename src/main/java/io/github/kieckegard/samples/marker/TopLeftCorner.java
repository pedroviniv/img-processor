/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class TopLeftCorner {
    
    private Integer x;
    private Integer y;

    public static class Builder {

        private Integer x;
        private Integer y;

        private Builder() {
        }

        public Builder x(final Integer value) {
            this.x = value;
            return this;
        }

        public Builder y(final Integer value) {
            this.y = value;
            return this;
        }

        public TopLeftCorner build() {
            return new io.github.kieckegard.samples.marker.TopLeftCorner(x, y);
        }
    }

    public static TopLeftCorner.Builder builder() {
        return new TopLeftCorner.Builder();
    }

    private TopLeftCorner(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "TopLeftCorner{" + "x=" + x + ", y=" + y + '}';
    }
}
