package com.programacion.controller;

import spark.Request;
import spark.Response;

import java.text.ParseException;

public interface BookService {

    String getBooks(Request request, Response response);
    String showAddBook(Request request,Response response);
    String addBook(Request request,Response response) throws ParseException;
    String showUpdateBook(Request request,Response response);
    String deleteBook(Request request,Response response);

}
