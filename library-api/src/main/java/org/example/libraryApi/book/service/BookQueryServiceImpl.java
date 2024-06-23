package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
                /*.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .toList();*/
    }

    @Override
    public Book getBookById(int id) {
        var book = bookRepository.getById(id);
        log.info("book from db: {}", book.toString());
        //if(book != null)
            //modelMapper.map(book, BookDTO.class);
        return book;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        var book = bookRepository.getByIsbn(isbn);
        if(book != null)
            modelMapper.map(book, Book.class);
        return null;
    }

    @Override
    public List<Book> getFreeBooks() {
        //TODO: получить с library-service id свободных книг и найти их через репозиторий
        return List.of();
    }
}
