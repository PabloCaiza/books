package com.programacion.controller;


import com.programacion.dto.Author;
import com.programacion.dto.Book;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Inject
    private WebTarget webTarget;

    @Override
    public String getBooks(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        var books= webTarget.path("/app-books/books")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Book>>() {
                });
        model.put("books", books);
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "libros"));
    }

    @Override
    public String showAddBook(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        List<Author> authors = webTarget.path("/app-authors/authors")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Author>>() {
                });
        model.put("book", new Book());
        model.put("authors", authors);
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "agregarLibros"));
    }

    private MultiMap mapRequestBook(Request request) {
        MultiMap params = new MultiMap<String>();
        UrlEncoded.decodeTo(request.body(), params, "UTF-8");
        return params;

    }

    void creteBook(Book book) {
        webTarget.path("/app-books/books")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));

    }

    void updateBook(Book book, String id) {
        webTarget.path(String.format("/app-books/books/%s", id))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));

    }

    @Override
    public String addBook(Request request, Response response) throws ParseException {
        var params=mapRequestBook(request);
        var book=Book.builder()
                .isbn(params.getString("isbn"))
                .author(Integer.parseInt(params.getString("author")))
                .title(params.getString("title"))
                .price(new BigDecimal(params.getString("price")))
                .build();
        String id = params.getString("id");
        if(!id.isEmpty()){
            book.setId(Integer.parseInt(id));
            updateBook(book,id);
        }else{
            creteBook(book);
        }
        response.redirect("/books");
        return "";
    }

    @Override
    public String showUpdateBook(Request request, Response response) {
        Integer id = Integer.parseInt(request.params(":id"));
        Map<String, Object> model = new HashMap<>();
        var book=webTarget.path(String.format("/app-books/books/%s", id))
                        .request(MediaType.APPLICATION_JSON_TYPE)
                                .get(Book.class);
        List<Author> authors = webTarget.path("/app-authors/authors")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Author>>() {
                });

        model.put("book", book);
        model.put("authors", authors);
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "agregarLibros"));
    }

    @Override
    public String deleteBook(Request request, Response response) {
        Integer id = Integer.parseInt(request.params(":id"));
        webTarget.path(String.format("/app-books/books/%d", id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        response.redirect("/books");
        return "";
    }
}
