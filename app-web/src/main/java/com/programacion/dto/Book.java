package com.programacion.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book{
        private Integer id;
        private String isbn;
        private String title;
        private Integer author;
        private BigDecimal price;
}
