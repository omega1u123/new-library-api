package org.example.libraryservice.repo;

import lombok.extern.slf4j.Slf4j;
import org.example.libraryservice.LibraryServiceApplication;
import org.example.libraryservice.domain.Book;
import org.example.libraryservice.domain.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LibraryServiceApplication.class)
@Slf4j
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addBook() {
        int id = 2;
        int result = bookRepository.addBook(id);
        assertEquals(1, result);
    }

    @Test
    public void takeBook(){
        var takenDate = LocalDate.now();
        var returnDate = takenDate.plusDays(7);
        var book = new Book(2, takenDate, returnDate);
        assertEquals(book, bookRepository.takeBook(book.getId(), takenDate, returnDate));
    }

    @Test
    public void returnBook(){
        assertEquals(2, bookRepository.returnBook(1));
    }

    @Test
    public void getFreeBooks(){
        assertEquals(List.of(1), bookRepository.getFreeBooks());
    }

    @Test
    public void deleteBook(){
        assertEquals(3, bookRepository.deleteBook(1));
    }

}
