package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Book addBook(Book book) {
        var savedBook = bookRepository.save(book);
        if(savedBook != null)
            return book;
        return null;
    }

    @Override
    public void takeBook(int id) {

    }

    @Override
    public void returnBook(Book book) {

    }

    @Override
    public boolean deleteBook(int id) {
        return bookRepository.removeById(id);
    }

    @Override
    public Book update(int id, Book book) {
        if(bookRepository.getById(id) != null){
            var updatedBook = bookRepository.updateBook(id, book);
            if(updatedBook != null)
                return updatedBook;
            return null;
        }
        return null;
    }
}
