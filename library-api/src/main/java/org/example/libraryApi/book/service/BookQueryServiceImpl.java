package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.externalApi.LibraryServiceApi;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.example.libraryApi.book.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            return bookRepository.getById(id);
        } catch (NoSuchElementException ex) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        try {
            return bookRepository.getByIsbn(isbn);
        } catch (NoSuchElementException ex) {
            throw new BookNotFoundException();
        }
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
