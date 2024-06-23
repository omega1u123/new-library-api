package org.example.libraryApi.book.service;

import org.example.libraryApi.book.domain.Book;

public interface BookCommandService {
    Book addBook(Book book);
    void takeBook(int id);
    void returnBook(Book book);
    boolean deleteBook(int id);
    Book update(int id, Book book);

}
