package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Find Book by id")
    @APIResponse(responseCode = "200", description = "book founded", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "400", description = "book not founded")
    public Book findById(@Parameter(description = "id of the book", required = true) @PathParam("id") Integer id)  {
        return bookService.getBookById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Books")
    @APIResponse(responseCode = "200", description = "all books", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    public List<Book> findAll() {
        return bookService.getBooks();
    }

    @GET
    @Path("/author/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "find book by authors")
    @APIResponse(responseCode = "200", description = "books founded", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    @APIResponse(responseCode = "400", description = "books not founded")
    public List<Book> findAllByAuthor(
            @Parameter(description = "id of the author", required = true)
            @PathParam("id") Integer id) {
        return bookService.getBookByAuthor(id);

    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "delete book by id")
    @APIResponse(responseCode = "204", description = "book deleted")
    @APIResponse(responseCode = "500", description = "problem with deleting book")
    public Response delete(
            @Parameter(description = "id of the book", required = true)
            @PathParam("id") Integer id) {
        bookService.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "create book")
    @APIResponse(responseCode = "201", description = "book created")
    @APIResponse(responseCode = "500", description = "problem with creating book")
    public Response create(
            @RequestBody(description = "Created book object", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b){
        bookService.creteBook(b);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "update book with specific id")
    @APIResponse(responseCode = "200", description = "book updated")
    @APIResponse(responseCode = "500", description = "problem with updating book")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "Updated book object", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b,
            @Parameter(description = "id of the book", required = true)
            @PathParam("id") Integer id){
        bookService.updateBook(id, b);
        return Response.status((Response.Status.OK)).build();
    }

}
