package org.example.libraryApi.book.exceptions;

public class BookNotSavedException extends RuntimeException {
    public BookNotSavedException() {
        super("book not saved");
    }
}
