package com.programacion.config;
import com.programacion.controller.AuthorService;
import com.programacion.controller.BookService;
import static spark.Spark.*;


public class WebServer {


    private AuthorService authorService;
    private BookService bookService;

    public WebServer(AuthorService authorService,
                     BookService bookService){

        this.authorService = authorService;
        this.bookService = bookService;
        port(8080);
        routes();
    }
    public void routes(){
        get("/",(request, response) -> authorService.getAuthors(request,response));
        get("/addAuthor",(request, response) -> authorService.showAddAuthor(request,response));
        post("/addAuthor",(request, response) -> authorService.addAuthor(request,response));
        get("/updateAuthor/:id",(request, response) -> authorService.showUpdateAuthor(request,response));
        get("/deleteAuthor/:id",(request, response) -> authorService.deleteAuthor(request,response));
        get("/books",(request, response) -> bookService.getBooks(request,response));
        get("/addBook",(request, response) -> bookService.showAddBook(request,response));
        post("/addBook",(request, response) -> bookService.addBook(request,response));
        get("/updateBook/:id",(request, response) -> bookService.showUpdateBook(request,response));
        get("/deleteBook/:id",(request, response) -> bookService.deleteBook(request,response));


    }



}
