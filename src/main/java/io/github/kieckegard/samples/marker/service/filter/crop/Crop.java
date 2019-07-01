/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter.crop;

import io.github.kieckegard.samples.marker.service.filter.BoundingBox;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Crop {
    
    private BoundingBox toBeCropped;

    public Crop(BoundingBox toBeCropped) {
        this.toBeCropped = toBeCropped;
    }

    public Crop() {
    }

    public BoundingBox getToBeCropped() {
        return toBeCropped;
    }

    public void setToBeCropped(BoundingBox toBeCropped) {
        this.toBeCropped = toBeCropped;
    }
}
