package com.bcnc.prueba.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.bcnc.prueba.application.repository")
@EntityScan("com.bcnc.prueba.application.model")
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }

}
