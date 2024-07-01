package org.example.libraryApi.book.exceptions;

public class BookNotDeletedException extends RuntimeException{
    public BookNotDeletedException(){
        super("book not deleted");
    }
}
