package com.github.alura.rendering.maprenderingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableDiscoveryClient
public class RenderingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RenderingApplication.class, args);
	}
}
