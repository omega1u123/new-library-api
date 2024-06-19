package org.example.newlibraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private int id;

    private String isbn;

    private String title;

    private String genre;

    private String description;

    private String author;


}
