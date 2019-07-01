/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker.service.common;

import io.github.kieckegard.samples.marker.service.filter.resize.ResizeFilter;
import io.github.kieckegard.samples.marker.service.filter.FilterTypes;
import io.github.kieckegard.samples.marker.service.filter.Filter;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.centered.CenteredCropFilter;
import io.github.kieckegard.samples.marker.service.filter.crop.CropFilter;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.cover.CoverResizeFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import io.github.kieckegard.samples.marker.service.filter.crop.modes.CropModes;
import io.github.kieckegard.samples.marker.service.filter.resize.modes.ResizeModes;

/**
 *
 * @author kieckegard
 */
public class JsonDeserializer {
    
    private Gson gson;
    
    public JsonDeserializer() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        
        RuntimeTypeAdapterFactory<Filter> filterTypes = RuntimeTypeAdapterFactory.of(Filter.class, "type")
                .registerSubtype(ResizeFilter.class, FilterTypes.RESIZE)
                .registerSubtype(CropFilter.class, FilterTypes.CROP);
        
        RuntimeTypeAdapterFactory<ResizeFilter> resizeFilterTypes = 
                RuntimeTypeAdapterFactory.of(ResizeFilter.class, "resizeMode")
                    .registerSubtype(CoverResizeFilter.class, ResizeModes.COVER);
        
        RuntimeTypeAdapterFactory<CropFilter> cropFilterTypes = 
                RuntimeTypeAdapterFactory.of(CropFilter.class, "cropMode")
                    .registerSubtype(CenteredCropFilter.class, CropModes.CENTERED);
        
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(resizeFilterTypes)
                .registerTypeAdapterFactory(cropFilterTypes)
                .registerTypeAdapterFactory(filterTypes)
                .create();
    }
    
    public <T> T deserialize(final String json, Class<T> clazz) {
        return this.gson.fromJson(json, clazz);
    }
}
