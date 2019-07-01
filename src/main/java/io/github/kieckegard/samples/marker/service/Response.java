/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Response {
    
    private String contentName;
    private BufferedImage content;

    public Response() {
    }

    public static class Builder {

        private String contentName;
        private BufferedImage content;

        private Builder() {
        }

        public Builder contentName(final String value) {
            this.contentName = value;
            return this;
        }

        public Builder content(final BufferedImage value) {
            this.content = value;
            return this;
        }

        public Response build() {
            return new io.github.kieckegard.samples.marker.service.Response(contentName, content);
        }
    }

    public static Response.Builder builder() {
        return new Response.Builder();
    }

    private Response(final String contentName, final BufferedImage content) {
        this.contentName = contentName;
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public BufferedImage getContent() {
        return content;
    }

    public void setContent(BufferedImage content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Response{" + "contentName=" + contentName + ", content=" + content + '}';
    }
}
