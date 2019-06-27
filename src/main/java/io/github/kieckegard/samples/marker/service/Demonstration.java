/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import io.github.kieckegard.samples.marker.service.crop.centered.CenteredCropFilterChain;
import io.github.kieckegard.samples.marker.service.resize.ResizeModes;
import io.github.kieckegard.samples.marker.service.crop.CropFilterChain;
import io.github.kieckegard.samples.marker.service.crop.CropModes;
import io.github.kieckegard.samples.marker.service.resize.ResizeFilterChain;
import io.github.kieckegard.samples.marker.service.resize.cover.CoverResizeModeChain;
import java.io.IOException;
import java.util.List;

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
    
    private static FilterContext context(Filter filter, String content) {
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        
        final String json = "{ \"layers\": [ { \"content\": \"https://www.iconsdb.com/icons/download/red/map-marker-2-512.png\" }, { \"content\": \"https://cdn130.picsart.com/234738423031212.png?r1024x1024\", \"filters\": [ { \"type\": \"resize\", \"resizeMode\": \"cover\", \"cover\": { \"boundingBox\": { \"width\": 263, \"height\": 263 } } }, { \"type\": \"crop\", \"cropMode\": \"centered\", \"crop\": { \"boundingBox\": { \"width\": 263, \"height\": 263 } } } ], \"position\": { \"distanceToTheTop\": 61, \"distanceToTheLeft\": 131 } } ] }";
        
        JsonDeserializer deserializer = new JsonDeserializer();
        
        Request request = deserializer.deserialize(json, Request.class);
        
        System.out.println(request);
        
        List<Filter> filters = request.getLayers().get(1).getFilters();
        ResizeFilter resize = (ResizeFilter) filters.get(0);
        CoverResizeFilter coverResizeFilter = (CoverResizeFilter) resize;
        System.out.println(coverResizeFilter.getCover());
        FilterChain filterChain = buildChain();
        
        /*List<Layer> layers = new ArrayList<>();
        
        layers.forEach(l -> {
            
            List<Filter> filters = l.getFilters();
            
            filters.stream()
                    .map(f -> {
                        return context(f, l.getContent());
                    })
                    .reduce((accumulated, next) -> {
                
                        filterChain.chain(next);
                        return next;
                    });
            
        });*/
    }
}
