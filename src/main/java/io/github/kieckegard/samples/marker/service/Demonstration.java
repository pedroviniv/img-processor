/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.common.JsonDeserializer;
import io.github.kieckegard.samples.marker.service.filter.FilterChain;
import io.github.kieckegard.samples.marker.service.filter.FilterService;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.centered.CenteredCropFilterChain;
import io.github.kieckegard.samples.marker.service.filter.crop.CropFilterChain;
import io.github.kieckegard.samples.marker.service.filter.resize.ResizeFilterChain;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.cover.CoverResizeModeChain;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class Demonstration {

    private static FilterChain buildChain() {

        CenteredCropFilterChain centeredCutFilter = new CenteredCropFilterChain();

        CropFilterChain cutFilter = new CropFilterChain(centeredCutFilter);

        CoverResizeModeChain coverResizeModeChain = new CoverResizeModeChain();
        ResizeFilterChain resizeFilter = new ResizeFilterChain(coverResizeModeChain);

        cutFilter.setNext(resizeFilter);

        return cutFilter;
    }

    public static void main(String[] args) throws IOException {

        final String json = "{ \"contentName\": \"marker.png\", \"layers\": [ { \"content\": \"https://www.iconsdb.com/icons/download/red/map-marker-2-512.png\" }, { \"behind\": true, \"content\": \"https://66.media.tumblr.com/6b10d3c75c34af5d8ea5bb29623430c6/tumblr_ouk6jozjaH1tiivhqo1_250.gif\", \"filters\": [ { \"type\": \"resize\", \"resizeMode\": \"cover\", \"cover\": { \"boundingBox\": { \"width\": 263, \"height\": 263 } } }, { \"type\": \"crop\", \"cropMode\": \"centered\", \"crop\": { \"toBeCropped\": { \"width\": 263, \"height\": 263 } } } ], \"position\": { \"distanceToTheTop\": 61, \"distanceToTheLeft\": 129 } } ] }";

        JsonDeserializer deserializer = new JsonDeserializer();

        Request request = deserializer.deserialize(json, Request.class);

        System.out.println(request);

        FilterChain filterChain = buildChain();

        List<Layer> layers = request.getLayers();
        
        FilterService filterService = new FilterService(filterChain);

        LayerService layerService = new LayerService(filterService);
        
        try {
            Response response = layerService.handle(request).get();
            String contentName = response.getContentName();
            BufferedImage content = response.getContent();
            
            ImageIO.write(content, "PNG", new File(contentName));
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
