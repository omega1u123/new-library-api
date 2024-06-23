package org.example.libraryApi.book.service;

import org.example.libraryApi.book.domain.Book;

import java.util.List;

public interface BookQueryService {

    List<Book> getAllBooks();
    Book getBookById(int id);
    Book getBookByIsbn(String isbn);
    List<Book> getFreeBooks();
}
