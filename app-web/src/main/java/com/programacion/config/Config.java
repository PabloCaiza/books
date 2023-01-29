package com.programacion.config;

;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;


@ApplicationScoped
public class Config {

    private final String BASE_URL="http://traefik";


    @ApplicationScoped
    @Produces
    public WebTarget webTarget(){
        Client client=ClientBuilder.newClient();
        return client.target(BASE_URL);
    }

}
