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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

        final String json = "{\n" +
"  \"contentName\": \"marker.png\",\n" +
"  \"layers\": [\n" +
"    {\n" +
"      \"content\": \"https://www.iconsdb.com/icons/download/red/map-marker-2-512.png\"\n" +
"    },\n" +
"    {\n" +
"      \"behind\": true,\n" +
"      \"content\": \"https://66.media.tumblr.com/6b10d3c75c34af5d8ea5bb29623430c6/tumblr_ouk6jozjaH1tiivhqo1_250.gif\",\n" +
"      \"filters\": [\n" +
"        {\n" +
"          \"type\": \"resize\",\n" +
"          \"resizeMode\": \"cover\",\n" +
"          \"cover\": {\n" +
"            \"boundingBox\": {\n" +
"              \"width\": 255,\n" +
"              \"height\": 132\n" +
"            }\n" +
"          }\n" +
"        },\n" +
"        {\n" +
"          \"type\": \"crop\",\n" +
"          \"cropMode\": \"centered\",\n" +
"          \"crop\": {\n" +
"            \"toBeCropped\": {\n" +
"              \"width\": 255,\n" +
"              \"height\": 132\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      ],\n" +
"      \"position\": {\n" +
"        \"distanceToTheTop\": 61,\n" +
"        \"distanceToTheLeft\": 129\n" +
"      }\n" +
"    },\n" +
"    {\n" +
"      \"behind\": true,\n" +
"      \"content\": \"https://i.ytimg.com/vi/bG_p3wMWddI/maxresdefault.jpg\",\n" +
"      \"filters\": [\n" +
"        {\n" +
"          \"type\": \"resize\",\n" +
"          \"resizeMode\": \"cover\",\n" +
"          \"cover\": {\n" +
"            \"boundingBox\": {\n" +
"              \"width\": 255,\n" +
"              \"height\": 132\n" +
"            }\n" +
"          }\n" +
"        },\n" +
"        {\n" +
"          \"type\": \"crop\",\n" +
"          \"cropMode\": \"centered\",\n" +
"          \"crop\": {\n" +
"            \"toBeCropped\": {\n" +
"              \"width\": 255,\n" +
"              \"height\": 132\n" +
"            }\n" +
"          }\n" +
"        }\n" +
"      ],\n" +
"      \"position\": {\n" +
"        \"distanceToTheTop\": 193,\n" +
"        \"distanceToTheLeft\": 129\n" +
"      }\n" +
"    }\n" +
"  ]\n" +
"}";

        JsonDeserializer deserializer = new JsonDeserializer();

        Request request = deserializer.deserialize(json, Request.class);

        System.out.println(request);

        FilterChain filterChain = buildChain();

        List<Layer> layers = request.getLayers();
        
        FilterService filterService = new FilterService(filterChain);
        LayerLoader layerLoader = new LayerLoader();
        LayerService layerService = new LayerService(filterService, layerLoader);
        
        List<Request> cem = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Request request1 = deserializer.deserialize(json, Request.class);
            cem.add(request1);
        }
        
        cem.parallelStream()
                .forEach(r -> {
                    
                    try {
                        Response response = layerService.handle(request).get();
                        String contentName = response.getContentName();
                        BufferedImage content = response.getContent();

                        ImageIO.write(content, "PNG", new File(UUID.randomUUID().toString()+contentName));

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        
        /*try {
            Response response = layerService.handle(request).get();
            String contentName = response.getContentName();
            BufferedImage content = response.getContent();
            
            ImageIO.write(content, "PNG", new File(UUID.randomUUID().toString()+contentName));
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Demonstration.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
