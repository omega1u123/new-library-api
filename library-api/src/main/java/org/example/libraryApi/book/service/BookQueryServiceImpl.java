package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.externalApi.LibraryServiceApi;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.example.libraryApi.book.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;
    private final LibraryServiceApi libraryServiceApi;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book getBookById(int id) {
        var book = bookRepository.getById(id);

        if (book == null) {
            throw new BookNotFoundException();
        }
        log.info("book from db: {}", book.toString());

        return book;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        var book = bookRepository.getByIsbn(isbn);

        if (book == null) {
            throw new BookNotFoundException();
        }

        return book;
    }

    @Override
    public List<Book> getAvailableBooks() {
        var response = libraryServiceApi.getFreeBooks();
        if (response.getStatusCode().is4xxClientError() || response.getBody() == null) {
            throw new BookNotFoundException();
        }
        return response.getBody().stream().map(bookRepository::getById).toList();
    }
}
