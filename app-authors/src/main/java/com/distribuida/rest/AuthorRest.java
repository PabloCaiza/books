package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.service.AuthorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@ApplicationScoped
@Path("/authors")
public class AuthorRest {
    @Inject
    private AuthorService authorService;


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET AUTHOR")
    @APIResponse(responseCode = "200", description = "AUTOR ENCONTRADO", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @APIResponse(responseCode = "400", description = "AUTOR NO ENCOTRADO")
    public Author findById(@Parameter(description = "ID DEL AUTOR", required = true) @PathParam("id") Long id) {
        return authorService.getAuthorById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET AUTHORS")
    @APIResponse(responseCode = "200", description = "OBTENER AUTORES", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Author.class)))
    public List<Author> findAll () {
        return authorService.getAuthors();
    }
    @DELETE
    @Path("/{id}")
    @Operation(summary = "DELETE AUTHOR")
    @APIResponse(responseCode = "204",description = "ELIMINAR AUTOR")
    public Response delete (
            @Parameter(description = "ID DEL AUTOR",required = true)
            @PathParam("id") Long id){
        authorService.delete(id);
        return Response.status((Response.Status.NO_CONTENT) ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "CREATE AUTHOR")
    @APIResponse(responseCode = "201", description = "CREAR AUTOR")
    @APIResponse(responseCode = "500", description = "PROBLEMA PARA CREAR AUTORES")
    public Response create(
            @RequestBody(description = "AUTOR QUE SERA CREADO", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author a)  {
        authorService.createAuthor(a);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "UPDATE AUTHOR")
    @APIResponse(responseCode = "200", description = "ACTUALIZAR AUTOR")
    @APIResponse(responseCode = "500", description = "PROBLEMA ACTUALIZAR AUTORES")
    public Response update (
            @RequestBody(description = "AUTOR AL SER ACTUALIZADO", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author a,
            @Parameter(description = "ID DEL AUTOR", required = true)
            @PathParam("id") Long id){
        authorService.updateAuthor(id,a);
        return Response.status((Response.Status.OK) ).build();
    }
}