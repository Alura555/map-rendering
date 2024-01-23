package com.github.alura.rendering.maprenderingservice.converter;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class GeometryReadConverter implements Converter<String, Geometry> {

    @Override
    public Geometry convert(String source) {
        WKTReader wktReader = new WKTReader();
        try {
            return wktReader.read(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse WKT string", e);
        }
    }
}