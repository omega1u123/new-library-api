package org.example.libraryApi.repo;

import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.LibraryApiApplication;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = LibraryApiApplication.class)
@Slf4j
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    private List<Book> books(){
        return List.of(
                new Book(
                "isbn1",
                "title1",
                "genre1",
                "description1",
                "author1"),
                new Book(
                        "isbn2",
                        "title1",
                        "genre1",
                        "description1",
                        "author1"),
                new Book(
                        "isbn3",
                        "title1",
                        "genre1",
                        "description1",
                        "author1"));
    }

    @Test
    void getAll(){
        assertThat(bookRepository.getAll()).isEqualTo(books());
    }

    @Test
    void save() {
        List<Book> books = books();;
        assertThat(bookRepository.save(books.get(0))).isEqualTo(books.get(0));

        assertThat(bookRepository.save(books.get(1))).isEqualTo(books.get(1));

        assertThat(bookRepository.save(books.get(2))).isEqualTo(books.get(2));
    }

    @Test
    void getById(){
        var book = new Book(1,
                "isbn1",
                "title1",
                "genre1",
                "description1",
                "author1");

        var bookFromDb = bookRepository.getById(1);

        assertThat(bookFromDb).isEqualTo(book);
    }

    @Test
    void getByIsbn(){

        List<Book> books = books();

        var isbn = books.get(0).getIsbn();

        assertThat(bookRepository.getByIsbn(isbn)).isEqualTo(books.get(0));
    }


    @Test
    void removeById(){
        assertThat(bookRepository.removeById(4)).isTrue();
    }

    @Test
    void update(){
        var id = 1;
        var book = new Book(
                "isbn1",
                "updated title",
                "genre1",
                "description1",
                "author1");

        assertThat(bookRepository.updateBook(id, book)).isEqualTo(book);
    }
}

