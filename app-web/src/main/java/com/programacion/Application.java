package com.programacion;


import com.programacion.config.WebServer;
import com.programacion.controller.AuthorService;
import com.programacion.controller.BookService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Application {

    public static void main(String[] args) {
        SeContainer seContainer = SeContainerInitializer
                .newInstance()
                .initialize();

        new WebServer(
                seContainer.select(AuthorService.class).get(),
                seContainer.select(BookService.class).get());
    }
}
