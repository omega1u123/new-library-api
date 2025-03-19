package org.example.libraryApi.book.exceptions;

public class BookNotReturnedException extends RuntimeException {
    public BookNotReturnedException() {
        super("book not returned");
    }
}
