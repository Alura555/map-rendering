CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE geo_object (
    id SERIAL PRIMARY KEY,
    geometry GEOMETRY NOT NULL,
    color_characteristic VARCHAR(255) NOT NULL
);