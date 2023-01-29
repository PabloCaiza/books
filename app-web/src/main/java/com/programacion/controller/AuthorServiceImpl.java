package com.programacion.controller;


import com.programacion.dto.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private WebTarget webTarget;

    @Override
    public String getAuthors(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        List<Author> authors = webTarget.path("/app-authors/authors")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<>() {
                });
        model.put("authors", authors);
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "autores"));
    }

    @Override
    public String showAddAuthor(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("author", new Author());
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "agregarAutores"));
    }


    void creteAuthor(Author author) {
        webTarget.path("/app-authors/authors")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(author, MediaType.APPLICATION_JSON_TYPE));

    }

    void updateAuthor(Author author, String id) {
        webTarget.path(String.format("/app-authors/authors/%s", id))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(author, MediaType.APPLICATION_JSON_TYPE));

    }

    private MultiMap mapRequestAuthor(Request request) {
        MultiMap params = new MultiMap<String>();
        UrlEncoded.decodeTo(request.body(), params, "UTF-8");
        return params;

    }

    @Override
    public String addAuthor(Request request, Response response) throws ParseException {
        var params = mapRequestAuthor(request);
        var author = Author.builder()
                .first_name(params.getString("firstName"))
                .last_name(params.getString("lastName"))
                .build();
        var id = params.getString("id");
        if (!id.isEmpty()) {
            author.setId(Integer.parseInt(id));
            updateAuthor(author, id);
        } else {
            creteAuthor(author);
        }
        response.redirect("/");
        return "";
    }

    @Override
    public String showUpdateAuthor(Request request, Response response) throws ParseException {
        Integer id = Integer.parseInt(request.params(":id"));
        Map<String, Object> model = new HashMap<>();
        var authorById = webTarget.path(String.format("/app-authors/authors/%s", id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Author.class);
        model.put("author", authorById);
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "agregarAutores"));
    }

    @Override
    public String deleteAuthor(Request request, Response response) {
        Integer id = Integer.parseInt(request.params(":id"));
        webTarget.path(String.format("/app-books/books/author/%d", id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        webTarget.path(String.format("/app-authors/authors/%d", id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        response.redirect("/");
        return "";
    }
}
