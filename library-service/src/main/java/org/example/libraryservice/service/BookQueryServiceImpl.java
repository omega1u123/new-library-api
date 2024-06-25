package org.example.libraryservice.service;

import lombok.RequiredArgsConstructor;
import org.example.libraryservice.domain.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService{

    private final BookRepository bookRepository;

    @Override
    public List<Integer> getFreeBooks() {
        return bookRepository.getFreeBooks();
    }
}
