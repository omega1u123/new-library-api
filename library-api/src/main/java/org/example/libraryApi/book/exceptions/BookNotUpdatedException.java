package org.example.libraryApi.book.exceptions;

public class BookNotUpdatedException extends RuntimeException{
    public BookNotUpdatedException(){
        super("book not updated");
    }
}
