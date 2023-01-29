package com.distribuida.service;

import com.distribuida.db.Author;
import com.distribuida.respository.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;


@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return authorRepository.listAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id);
    }


    @Transactional
    @Override
    public void createAuthor(Author author) {
        authorRepository.persist(author);

    }
    @Transactional
    @Override
    public void updateAuthor(Long id, Author author) {
        Author savedAuthor=getAuthorById(id);
        savedAuthor.setFirst_name(author.getFirst_name());
        savedAuthor.setLast_name(author.getLast_name());
        authorRepository.persist(savedAuthor);

    }

    @Transactional
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
