/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.resize.modes.cover;

import io.github.kieckegard.samples.marker.BoundingBox;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Cover {
    
    private BoundingBox boundingBox;

    public Cover(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Cover() {
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }
}
