package com.github.alura.rendering.maprenderingservice.repository;

import com.github.alura.rendering.maprenderingservice.entity.GeoObject;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GeoObjectRepository extends R2dbcRepository<GeoObject, Long> {
    @Query("SELECT * FROM geo_object WHERE ST_Within(geometry, ST_MakeEnvelope(:minLon, :minLat, :maxLon, :maxLat, 3857))")
    Flux<GeoObject> findByBoundingBox(double minLon, double minLat, double maxLon, double maxLat);
}
