package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
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
        Book savedBook = bookRepository.save(book);
        log.info("sending mess tp add_book-topic");
        kafkaTemplate.send("add_book-topic", String.valueOf(book.getId()));
        return book;
    }

    @Override
    public void takeBook(int bookId) {
        kafkaTemplate.send("take_book-topic", String.valueOf(bookId));
    }

    @Override
    public void returnBook(int bookId) {
        kafkaTemplate.send("return_book-topic", String.valueOf(bookId));
    }

    @Override
    public boolean deleteBook(int bookId) {
        kafkaTemplate.send("delete_book-topic", String.valueOf(bookId));
        return bookRepository.removeById(bookId);
    }

    @Override
    public Book update(int bookId, Book book) {
        if(bookRepository.getById(bookId) != null){
            var updatedBook = bookRepository.updateBook(bookId, book);
            if(updatedBook != null)
                return updatedBook;
            return null;
        }
        return null;
    }
}
