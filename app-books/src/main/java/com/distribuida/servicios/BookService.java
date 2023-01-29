package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookService {

    List<Book> getBooks() throws ExecutionException, InterruptedException;
    Book getBookById(Integer id) throws ExecutionException, InterruptedException;
    void creteBook(Book book) throws ExecutionException, InterruptedException;
    void updateBook(Integer id,Book book) throws ExecutionException, InterruptedException;
    void delete(Integer id) throws ExecutionException, InterruptedException;

    void deleteBookByAuthor(Integer authorId) throws ExecutionException, InterruptedException;

}
