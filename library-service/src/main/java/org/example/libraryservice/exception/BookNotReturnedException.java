package org.example.libraryservice.exception;

public class BookNotReturnedException extends RuntimeException {
    public BookNotReturnedException(int bookId) {
        super("book with id=" + bookId + " not returned");
    }
}
