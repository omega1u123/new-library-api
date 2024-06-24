package org.example.libraryservice.service;

import org.example.libraryservice.domain.Book;

public interface BookCommandService {

    void addBook(int id);
    Book takeBook(int id);
    void returnBook(int id);
    boolean deleteBook(int id);

}
