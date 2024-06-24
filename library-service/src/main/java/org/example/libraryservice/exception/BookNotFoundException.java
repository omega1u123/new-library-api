package org.example.libraryservice.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(int bookId){
        super("book with id=" + bookId + " not found");
    }
}
