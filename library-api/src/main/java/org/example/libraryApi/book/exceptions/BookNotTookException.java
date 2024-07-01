package org.example.libraryApi.book.exceptions;

public class BookNotTookException extends RuntimeException{
    public BookNotTookException(){
        super("book not took");
    }
}
