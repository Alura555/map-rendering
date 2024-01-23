package com.github.alura.rendering.maprenderingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.alura.rendering.maprenderingservice.entity.GeoObject;
import com.github.alura.rendering.maprenderingservice.service.GeoObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GeoObjectController {

    private final GeoObjectService geoObjectService;

    public GeoObjectController(GeoObjectService geoObjectService) {
        this.geoObjectService = geoObjectService;
    }


    @GetMapping("/")
    public Flux<GeoObject> getGeoObjects(
            @RequestParam int width,
            @RequestParam int height,
            @RequestParam String bbox) throws JsonProcessingException {

        return geoObjectService.findByBorders(bbox);
    }
}
