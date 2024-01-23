package com.github.alura.rendering.maprenderingservice.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.io.IOException;

public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            String wkt = jsonParser.getValueAsString();
            return new WKTReader().read(wkt);
        } catch (ParseException e) {
            throw new IOException("Error parsing WKT", e);
        }
    }
}

