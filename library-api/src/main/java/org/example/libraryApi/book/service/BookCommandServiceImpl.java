package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.example.libraryApi.book.exceptions.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Book addBook(Book book) {
        try {
            bookRepository.save(book);
            log.info("sending mess tp add_book-topic");
            kafkaTemplate.send("add_book-topic", String.valueOf(book.getId()));
            return book;
        }catch (RuntimeException ex){
            throw new BookNotSavedException();
        }
    }

    @Override
    public void takeBook(int bookId) {
        try {
            kafkaTemplate.send("take_book-topic", String.valueOf(bookId));
        }catch (RuntimeException ex){
            throw new BookNotTookException();
        }
    }

    @Override
    public void returnBook(int bookId) {
        try {
            kafkaTemplate.send("return_book-topic", String.valueOf(bookId));
        }catch (RuntimeException ex){
            throw new BookNotReturnedException();
        }
    }

    @Override
    public boolean deleteBook(int bookId) {
        kafkaTemplate.send("delete_book-topic", String.valueOf(bookId));
        if(!bookRepository.removeById(bookId))
            throw new BookNotDeletedException();
        return true;
    }

    @Override
    public Book update(int bookId, Book book) {
        if(bookRepository.getById(bookId) != null){
            return bookRepository.updateBook(bookId, book);
        }else{
            throw new BookNotUpdatedException();
        }
    }
}
