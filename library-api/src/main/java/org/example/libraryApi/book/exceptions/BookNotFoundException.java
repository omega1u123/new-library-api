package org.example.libraryApi.book.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("book not found");
    }
}
