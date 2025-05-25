package com.bcnc.prueba.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
@EnableJpaRepositories("com.bcnc.prueba.application.repository")
@EntityScan("com.bcnc.prueba.application.model")
public class RepositoryConfig {
}
