package com.github.alura.rendering.maprenderingservice.service.implementation;

import com.github.alura.rendering.maprenderingservice.entity.GeoObject;
import com.github.alura.rendering.maprenderingservice.repository.GeoObjectRepository;
import com.github.alura.rendering.maprenderingservice.service.GeoObjectService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GeoObjectServiceImpl implements GeoObjectService {

    private final GeoObjectRepository geoObjectRepository;

    public GeoObjectServiceImpl(GeoObjectRepository geoObjectRepository) {
        this.geoObjectRepository = geoObjectRepository;
    }
    @Override
    public Flux<GeoObject> findByBorders(String bbox) {
        String[] bboxParts = bbox.split(",");

        if (bboxParts.length != 4) {
            throw new IllegalArgumentException("Invalid number of parameters in bbox");
        }

        double minLon = Double.parseDouble(bboxParts[0]);
        double minLat = Double.parseDouble(bboxParts[1]);
        double maxLon = Double.parseDouble(bboxParts[2]);
        double maxLat = Double.parseDouble(bboxParts[3]);

        return geoObjectRepository.findByBoundingBox(minLon, minLat, maxLon, maxLat);
    }
}
