package com.avangarde.parkinglot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan("com.avangarde.parkinglot")
public class AppConfig {
    private final EntityManagerFactory entityManagerFactory;

    public AppConfig() {
        this.entityManagerFactory = Persistence
                .createEntityManagerFactory("com.avangarde.parkinglot");
    }

    @Bean
    @Scope("prototype")
    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }
}
