package org.example.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryservice.domain.Book;
import org.example.libraryservice.domain.repo.BookRepository;
import org.example.libraryservice.exception.BookNotDeletedException;
import org.example.libraryservice.exception.BookNotFoundException;
import org.example.libraryservice.exception.BookNotReturnedException;
import org.example.libraryservice.exception.BookNotSavedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;

    @Override
    public void addBook(int id) {
        var res = bookRepository.addBook(id);
        if (res != 1) {
            throw new BookNotSavedException();
        }
    }

    @Override
    public Book takeBook(int id) {
        var bookFromDb = bookRepository.getById(id);
        if (bookFromDb == null) {
            throw new BookNotFoundException(id);
        }

        var takeDate = LocalDate.now();
        var returnDate = takeDate.plusDays(7);

        bookFromDb.setTakenDate(takeDate);
        bookFromDb.setReturnDate(returnDate);

        return bookRepository.takeBook(bookFromDb);
    }

    @Override
    public void returnBook(int id) {
        if (bookRepository.getById(id) == null)
            throw new BookNotFoundException(id);

        var res = bookRepository.returnBook(id);
        log.info("bookRepository.returnBook return : {}", res);
        if (res != 2) {
            throw new BookNotReturnedException(id);
        }
    }

    @Override
    public boolean deleteBook(int id) {
        if (bookRepository.getById(id) == null)
            throw new BookNotFoundException(id);

        var res = bookRepository.deleteBook(id);
        if (res != 3) {
            throw new BookNotDeletedException(id);
        }

        return true;
    }

}
