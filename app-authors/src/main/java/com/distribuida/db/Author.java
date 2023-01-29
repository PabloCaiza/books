package com.distribuida.db;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "authors")
@Data
public class Author{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;

}
