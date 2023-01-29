package com.distribuida.servicios;

import com.distribuida.db.Book;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject
    private DbClient dbClient;

    @Override
    public List<Book> getBooks() throws ExecutionException, InterruptedException {
        return dbClient.execute(dbExecute -> dbExecute.createQuery("SELECT * FROM books").execute())
                .collectList()
                .get()
                .stream().map(dbRow -> Book
                        .builder()
                        .id(dbRow.column("id").as(Integer.class))
                        .title(dbRow.column("title").as(String.class))
                        .isbn(dbRow.column("isbn").as(String.class))
                        .author(dbRow.column("author_id").as(Integer.class))
                        .price(dbRow.column("price").as(BigDecimal.class))
                        .build())
                .collect(Collectors.toList());


    }

    @Override
    public Book getBookById(Integer id) throws ExecutionException, InterruptedException {

        return dbClient.execute(dbExecute -> dbExecute
                .createQuery("SELECT * FROM books WHERE id=?").addParam(id).execute())
                .first()
                .map(dbRow ->  Book
                        .builder()
                        .id(dbRow.column("id").as(Integer.class))
                        .title(dbRow.column("title").as(String.class))
                        .isbn(dbRow.column("isbn").as(String.class))
                        .author(dbRow.column("author_id").as(Integer.class))
                        .price(dbRow.column("price").as(BigDecimal.class))
                        .build())
                .get();

    }


    @Override
    public void creteBook(Book book) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.insert("INSERT INTO books(isbn,title,author_id,price) VALUES(?,?,?,?)",
                        book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPrice()))
                .get();
    }

    @Override
    public void updateBook(Integer id, Book book) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute ->dbExecute.createUpdate("UPDATE books SET isbn=?,title=?,author_id=?,price=? WHERE id=?")
                        .addParam(book.getIsbn())
                        .addParam(book.getTitle())
                        .addParam(book.getAuthor())
                        .addParam(book.getPrice())
                        .addParam(id)
                        .execute())
                .get();


    }

    @Override
    public void delete(Integer id) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.createDelete("DELETE FROM books WHERE id=?")
                .addParam(id)
                .execute())
                .get();

    }

    @Override
    public void deleteBookByAuthor(Integer authorId) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.createDelete("DELETE FROM books WHERE author_id=? ")
                .addParam(authorId).execute())
                .get();
    }
}
