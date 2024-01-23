package com.github.alura.rendering.maprenderingservice.converter;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class GeometryWriteConverter /*extends StdSerializer<Geometry> */implements Converter<Geometry, String>  {

//    public GeometryWriteConverter() {
//        this(null);
//    }
//
//    protected GeometryWriteConverter(Class<Geometry> t) {
//        super(t);
//    }

    @Override
    public String convert(Geometry source) {
        WKTWriter wktWriter = new WKTWriter();
        return wktWriter.write(source);
    }

   /* @Override
    public void serialize(Geometry geometry,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());
        String geometryJson = mapper.writeValueAsString(geometry);
        jsonGenerator.writeObject(geometry);
    }*/
}