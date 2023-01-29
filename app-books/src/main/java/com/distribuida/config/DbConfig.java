package com.distribuida.config;


import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;



@ApplicationScoped
public class DbConfig {

    @Produces
    @ApplicationScoped
    public DbClient dbClient(){
        Config config=Config.create();
        return DbClient.builder()
                .config(config.get("db"))
                .build();
    }


}
