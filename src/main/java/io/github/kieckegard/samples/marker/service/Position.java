/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Position {
    
    private Integer distanceToTheTop;
    private Integer distanceToTheLeft;

    public Position() {
    }

    public Position(Integer distanceToTheTop, Integer distanceToTheLeft) {
        this.distanceToTheTop = distanceToTheTop;
        this.distanceToTheLeft = distanceToTheLeft;
    }    
    
    public Integer getDistanceToTheTop() {
        return distanceToTheTop;
    }

    public void setDistanceToTheTop(Integer distanceToTheTop) {
        this.distanceToTheTop = distanceToTheTop;
    }

    public Integer getDistanceToTheLeft() {
        return distanceToTheLeft;
    }

    public void setDistanceToTheLeft(Integer distanceToTheLeft) {
        this.distanceToTheLeft = distanceToTheLeft;
    }

    @Override
    public String toString() {
        return "Position{" + "distanceToTheTop=" + distanceToTheTop + ", distanceToTheLeft=" + distanceToTheLeft + '}';
    }
}
