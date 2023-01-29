package com.distribuida.service;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();
    Author getAuthorById(Long id);
    void createAuthor(Author author);
    void updateAuthor(Long id,Author author);
    void delete(Long id);
}
