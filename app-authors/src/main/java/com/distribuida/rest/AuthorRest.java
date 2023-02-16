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
    @Operation(summary = "Find Author by id")
    @APIResponse(responseCode = "200", description = "author founded", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @APIResponse(responseCode = "400", description = "author not founded")
    public Author findById(@Parameter(description = "id of the author", required = true) @PathParam("id") Long id) {
        return authorService.getAuthorById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Authors")
    @APIResponse(responseCode = "200", description = "all authors", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Author.class)))
    public List<Author> findAll () {
        return authorService.getAuthors();
    }
    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") Long id){
        authorService.delete(id);
        return Response.status((Response.Status.NO_CONTENT) ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "create author")
    @APIResponse(responseCode = "201", description = "author created")
    @APIResponse(responseCode = "500", description = "problem with creating author")
    public Response create(
            @RequestBody(description = "Created author object", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author a)  {
        authorService.createAuthor(a);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "update author with specific id")
    @APIResponse(responseCode = "200", description = "author updated")
    @APIResponse(responseCode = "500", description = "problem with updating author")
    public Response update (
            @RequestBody(description = "Updated book object", required = true,
                    content = @Content(schema = @Schema(implementation = Author.class)))
            Author a,
            @Parameter(description = "id of the book", required = true)
            @PathParam("id") Long id){
        authorService.updateAuthor(id,a);
        return Response.status((Response.Status.OK) ).build();
    }
}