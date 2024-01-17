package com.github.alura.rendering.maprenderingservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeoObject {
    @Id
    private Long id;
    private Geometry geometry;
    private String colorCharacteristic;
}
