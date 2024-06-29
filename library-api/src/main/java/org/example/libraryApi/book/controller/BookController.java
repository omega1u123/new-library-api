package org.example.libraryApi.book.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.service.BookCommandService;
import org.example.libraryApi.book.service.BookQueryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookQueryService bookQueryService;
    private final BookCommandService bookCommandService;

    @QueryMapping
    public List<Book> getAll(){
        return bookQueryService.getAllBooks();
    }

    @QueryMapping
    public Book getById(@Argument int id){
        return bookQueryService.getBookById(id);
    }

    @MutationMapping
    public Book addBook(@Argument Book book){
        return bookCommandService.addBook(book);
    }

    @MutationMapping
    public Book updateBook(@Argument int id, @Argument Book updatedBook){
        return bookCommandService.update(id, updatedBook);
    }

    @MutationMapping
    public String removeBook(@Argument int id){

        if(bookCommandService.deleteBook(id))
            return "book deleted";

        return "book not deleted";
    }

    @MutationMapping
    public String takeBook(@Argument int id){
        bookCommandService.takeBook(id);

        return "book took";
    }

    @MutationMapping
    public String returnBook(@Argument int id){
        bookCommandService.returnBook(id);
        return "book returned";
    }

    @QueryMapping
    public List<Book> getAvailableBooks(){
        try {
            return bookQueryService.getAvailableBooks();
        }catch (InterruptedException ex){
            return null;
        }

    }

}
