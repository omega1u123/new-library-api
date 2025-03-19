package org.example.libraryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class BookCommandServiceImplTests {

    @Autowired
    private BookCommandServiceImpl bookCommandService;

    @Test
    public void deleteBook(){
        assertTrue(bookCommandService.deleteBook(5));
    }

}
