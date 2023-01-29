package com.programacion.controller;

import spark.Request;
import spark.Response;

import java.text.ParseException;

public interface AuthorService {

    String getAuthors(Request request, Response response);

    String showAddAuthor(Request request, Response response);

    String addAuthor(Request request, Response response) throws ParseException;

    String showUpdateAuthor(Request request, Response response) throws ParseException;

    String deleteAuthor(Request request, Response response);
}
