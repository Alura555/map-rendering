package com.github.alura.rendering.maprenderingservice.config;

import com.github.alura.rendering.maprenderingservice.converter.GeometryReadConverter;
import com.github.alura.rendering.maprenderingservice.converter.GeometryWriteConverter;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .username("user")
                        .password("password")
                        .database("objects_db")
                        .build());
    }

    @Override
    protected List<Object> getCustomConverters() {
        List<Object> converterList = new ArrayList<>();
        converterList.add(new GeometryReadConverter());
        converterList.add(new GeometryWriteConverter());
        return converterList;
    }
}