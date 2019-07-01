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
public class LayerLoadingException extends RuntimeException {
    
    private final String failedContent;

    public LayerLoadingException(String failedContent, String message) {
        super(message);
        this.failedContent = failedContent;
    }

    public LayerLoadingException(String failedContent, String message, Throwable cause) {
        super(message, cause);
        this.failedContent = failedContent;
    }

    public LayerLoadingException(String failedContent, Throwable cause) {
        super(cause);
        this.failedContent = failedContent;
    }

    public String getFailedContent() {
        return failedContent;
    }
}
