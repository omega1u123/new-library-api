package org.example.libraryservice.exception;

public class BookNotSavedException extends RuntimeException {
    public BookNotSavedException() {
        super("book not saved");
    }
}
