package org.example.libraryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryservice.domain.repo.BookRepository;
import org.example.libraryservice.exception.BookNotFoundDbException;
import org.example.libraryservice.exception.BookNotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;

    @Override
    public List<Integer> getFreeBooks() {
        try {
            List<Integer> freeBooks = bookRepository.getFreeBooks();
            if (freeBooks.isEmpty()) {
                throw new BookNotFoundException();
            }
            return freeBooks;
        } catch (BookNotFoundDbException ex) {
            throw new BookNotFoundException();
        }

    }
}
