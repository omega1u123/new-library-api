package org.example.libraryApi.service;

import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.example.libraryApi.book.service.BookCommandServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BookCommandServiceTests {

    @Mock
    private BookRepository bookRepository;



    @InjectMocks
    private BookCommandServiceImpl bookCommandService;
/*
    @Test
    void addBook(){
        var book = new Book(1, "qwe", "qwe", "asd", "asd", "asd");
        var bookDTO = new BookDTO(1, "qwe", "qwe", "asd", "asd", "asd");

        Mockito.when(bookRepository.save(book)).thenReturn(true);
        Mockito.when(modelMapper.map(bookDTO, Book.class))
                .thenReturn(book);

        assertThat(bookCommandService.addBook(book)).isTrue();
    }
*/
}
