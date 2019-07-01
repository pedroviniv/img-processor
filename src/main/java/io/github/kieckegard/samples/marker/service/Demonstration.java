/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service;

import io.github.kieckegard.samples.marker.service.resize.modes.cover.CoverResizeFilter;
import io.github.kieckegard.samples.marker.service.crop.modes.centered.CenteredCropFilterChain;
import io.github.kieckegard.samples.marker.service.crop.CropFilterChain;
import io.github.kieckegard.samples.marker.service.resize.ResizeFilterChain;
import io.github.kieckegard.samples.marker.service.resize.modes.cover.CoverResizeModeChain;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private static FilterContext context(Filter filter, String content) {
        return null;
    }

    private static BufferedImage load(final String url) throws IOException {
        URL urlInstance = new URL(url);
        BufferedImage loadedContent = ImageIO.read(urlInstance);
        return loadedContent;
    }

    private static ProcessedLayer load(final Layer layer) {

        try {
            String contentUrl = layer.getContent();
            BufferedImage content = load(contentUrl);
            return ProcessedLayer.builder()
                    .layer(layer)
                    .loadedImage(content)
                    .build();
        } catch (IOException ex) {
            throw new LayerLoadingException(layer.getContent(), ex);
        }
    }

    public static void main(String[] args) throws IOException {

        final String json = "{ \"layers\": [ { \"content\": \"https://www.iconsdb.com/icons/download/red/map-marker-2-512.png\" }, { \"behind\": true, \"content\": \"https://66.media.tumblr.com/6b10d3c75c34af5d8ea5bb29623430c6/tumblr_ouk6jozjaH1tiivhqo1_250.gif\", \"filters\": [ { \"type\": \"resize\", \"resizeMode\": \"cover\", \"cover\": { \"boundingBox\": { \"width\": 263, \"height\": 263 } } }, { \"type\": \"crop\", \"cropMode\": \"centered\", \"crop\": { \"toBeCropped\": { \"width\": 263, \"height\": 263 } } } ], \"position\": { \"distanceToTheTop\": 61, \"distanceToTheLeft\": 129 } } ] }";

        JsonDeserializer deserializer = new JsonDeserializer();

        Request request = deserializer.deserialize(json, Request.class);

        System.out.println(request);

        // List<Filter> filters = request.getLayers().get(1).getFilters();
        // ResizeFilter resize = (ResizeFilter) filters.get(0);
        // CoverResizeFilter coverResizeFilter = (CoverResizeFilter) resize;
        // System.out.println(coverResizeFilter.getCover());
        FilterChain filterChain = buildChain();

        List<Layer> layers = request.getLayers();
        
        FilterService filterService = new FilterService(filterChain);

        LayerService layerService = new LayerService(filterService);
        layerService.process(layers);
        
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
