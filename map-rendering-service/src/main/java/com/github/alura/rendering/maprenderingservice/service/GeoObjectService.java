package com.github.alura.rendering.maprenderingservice.service;

import com.github.alura.rendering.maprenderingservice.entity.GeoObject;
import reactor.core.publisher.Flux;

public interface GeoObjectService {
    Flux<GeoObject> findByBorders(String bbox);
}
