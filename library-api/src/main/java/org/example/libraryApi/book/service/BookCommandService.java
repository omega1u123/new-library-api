package org.example.libraryApi.book.service;

import org.example.libraryApi.book.domain.Book;

public interface BookCommandService {
    Book addBook(Book book);
    void takeBook(int bookId);
    void returnBook(int bookId);
    boolean deleteBook(int bookId);
    Book update(int id, Book book);

}
