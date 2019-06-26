/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

/**
 *
 * @author kieckegard
 */
public class CropFilter extends Filter {
    
    private String cropMode;
    
    public CropFilter(final String cropMode) {
        super(FilterTypes.CROP);
        this.cropMode = cropMode;
    }

    public String getCropMode() {
        return cropMode;
    }

    @Deprecated
    public void setCropMode(String cropMode) {
        this.cropMode = cropMode;
    }

    @Override
    public String toString() {
        return "CropFilter{" + "cropMode=" + cropMode + '}';
    }
}
