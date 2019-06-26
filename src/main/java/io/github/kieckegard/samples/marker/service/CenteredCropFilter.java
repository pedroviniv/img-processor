/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.crop.Crop;
import io.github.kieckegard.samples.marker.service.crop.CropModes;

/**
 *
 * @author kieckegard
 */
public class CenteredCropFilter extends CropFilter {
    
    private Crop crop;
    
    public CenteredCropFilter(final Crop crop) {
        super(CropModes.CENTERED);
        this.crop = crop;
    }

    public Crop getCrop() {
        return crop;
    }

    @Deprecated
    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    @Override
    public String toString() {
        return "CenteredCropFilter{" + "crop=" + crop + '}';
    }
}