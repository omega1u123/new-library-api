package org.example.libraryservice.exception;

public class BookNotDeletedException extends RuntimeException{
    public BookNotDeletedException(int bookId){
        super("book with id=" + bookId + " not deleted");
    }
}
