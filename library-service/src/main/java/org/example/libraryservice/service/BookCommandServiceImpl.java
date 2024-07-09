package org.example.libraryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryservice.domain.Book;
import org.example.libraryservice.domain.repo.BookRepository;
import org.example.libraryservice.exception.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;

    @Override
    public void addBook(int id) {
        try {
            bookRepository.addBook(id);
        } catch (BookNotSavedDbException ex) {
            throw new BookNotSavedException();
        }
    }

    @Override
    public Book takeBook(int id) {
        try {
            var bookFromDb = bookRepository.getById(id);
            var takeDate = LocalDate.now();
            var returnDate = takeDate.plusDays(7);
            bookFromDb.setTakenDate(takeDate);
            bookFromDb.setReturnDate(returnDate);
            return bookRepository.takeBook(bookFromDb);
        } catch (NoSuchElementException ex) {
            throw new BookNotFoundException();
        } catch (BookNotTakenDbException ex) {
            throw new BookNotTakenException();
        }
    }

    @Override
    public void returnBook(int id) {
        try {
            bookRepository.getById(id);
            bookRepository.returnBook(id);
        } catch (NoSuchElementException ex) {
            throw new BookNotFoundException();
        } catch (BookNotReturnedDbException ex) {
            throw new BookNotReturnedException(id);
        }
    }

    @Override
    public void deleteBook(int id) {
        try {
            bookRepository.getById(id);
            bookRepository.deleteBook(id);
        } catch (NoSuchElementException ex) {
            throw new BookNotFoundException();
        } catch (BookNotDeletedDbException ex) {
            throw new BookNotDeletedException(id);
        }
    }

}
