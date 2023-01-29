package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.service.AuthorService;

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
    public Author findById(@PathParam("id") Long id) {
        return authorService.getAuthorById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    public Response create(Author a)  {
        authorService.createAuthor(a);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (Author a, @PathParam("id") Long id){
        authorService.updateAuthor(id,a);
        return Response.status((Response.Status.OK) ).build();
    }
}