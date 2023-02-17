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
    @Operation(summary = "GET BOOK")
    @APIResponse(responseCode = "200", description = "LIBRO ENCONTRADO", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "400", description = "LIBRO NO ENCONTRADO")
    public Book findById(@Parameter(description = "ID BOOK", required = true) @PathParam("id") Integer id)  {
        return bookService.getBookById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET BOOKS")
    @APIResponse(responseCode = "200", description = "TODOS LOS LIBROS", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    public List<Book> findAll() {
        return bookService.getBooks();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "DELETED BOOK")
    @APIResponse(responseCode = "204", description = "LIBRO ELIMINDADO")
    @APIResponse(responseCode = "500", description = "PROBLEMA CON EL SERVIDOR")
    public Response delete(
            @Parameter(description = "ID BOOK", required = true)
            @PathParam("id") Integer id) {
        bookService.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "CREATE BOOK")
    @APIResponse(responseCode = "201", description = "LIBRO CREADO")
    @APIResponse(responseCode = "500", description = "PROBLEMA CON EL SERVIDOR")
    public Response create(
            @RequestBody(description = "LIBRO QUE VA HACER INSERTADO", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b){
        bookService.creteBook(b);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "UPDATE BOOK")
    @APIResponse(responseCode = "200", description = "LIBRO ACTULIZADO")
    @APIResponse(responseCode = "500", description = "PROBOEMA EN LA ACTUALIZACION")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "DATOS DEL LIBRO PARA ACTUALIZAR", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b,
            @Parameter(description = "ID BOOK", required = true)
            @PathParam("id") Integer id){
        bookService.updateBook(id, b);
        return Response.status((Response.Status.OK)).build();
    }

}
