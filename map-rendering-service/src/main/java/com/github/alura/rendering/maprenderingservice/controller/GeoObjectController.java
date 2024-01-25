package com.github.alura.rendering.maprenderingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.alura.rendering.maprenderingservice.entity.GeoObject;
import com.github.alura.rendering.maprenderingservice.service.GeoObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GeoObjectController {

    private final GeoObjectService geoObjectService;

    public GeoObjectController(GeoObjectService geoObjectService) {
        this.geoObjectService = geoObjectService;
    }


    @GetMapping(value = "/", produces = "application/json")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    @CrossOrigin
    public Flux<GeoObject> getGeoObjects(
            @RequestParam(defaultValue = "100") int width,
            @RequestParam(defaultValue = "100") int height,
            @RequestParam String bbox){
        return geoObjectService.findByBorders(bbox);
    }
}
