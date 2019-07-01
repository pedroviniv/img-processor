/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.filter;

/**
 *
 * @author kieckegard
 */

public class Filter {
    
    private String type;

    public Filter(String type) {
        this.type = type;
    }

    public Filter() {
    }

    public String getType() {
        return type;
    }

    @Deprecated
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Filter2{" + "type=" + type + '}';
    }
}
