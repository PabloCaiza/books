package com.distribuida.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@ApplicationScoped
public class DbConfig {


    @Inject
    @ConfigProperty(name = "db.user")
    private String username;
    @Inject
    @ConfigProperty(name = "db.password")
    private String password;
    @Inject
    @ConfigProperty(name = "db.url")
    private String url;



    @Produces
    @ApplicationScoped
    public EntityManager entityManager(){
        Map<String,String> properties=new HashMap<>();
        properties.put("jakarta.persistence.jdbc.user",username);
        properties.put("jakarta.persistence.jdbc.password",password);
        properties.put("jakarta.persistence.jdbc.url",url);
        properties.put("jakarta.persistence.jdbc.driver","org.postgresql.Driver");
        System.out.println(properties);
        return Persistence.createEntityManagerFactory("book.jpa",properties)
                .createEntityManager();
    }


}
