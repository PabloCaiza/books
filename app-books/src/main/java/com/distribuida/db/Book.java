package com.distribuida.db;

import jakarta.inject.Inject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "book",description = "POJO that represent a book")
@Table(name = "books")
@NamedQuery(name = "Book.findAll",query = "SELECT b FROM Book b")
@NamedQuery(name = "Book.findByAuthor",query = "SELECT b FROM Book b WHERE b.author= :authorId")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Schema(required = true,example = "1234")
    @Column(name = "isbn")
    private String isbn;
    @Schema(required = true,example = "title")
    @Column(name = "title")
    private String title;
    @Schema(required = true,example = "pablo")
    @Column(name = "author_id")
    private Integer author;
    @Schema(required = true,example = "20.00")
    @Column(name = "price")
    private BigDecimal price;
}
