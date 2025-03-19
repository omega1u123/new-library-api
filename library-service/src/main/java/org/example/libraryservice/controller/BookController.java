package org.example.libraryservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.libraryservice.exception.BookNotFoundException;
import org.example.libraryservice.service.BookQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookQueryService bookQueryService;

    @GetMapping("/getFreeBooks")
    public ResponseEntity<?> getFreeBooks() {
        return ResponseEntity.ok(bookQueryService.getFreeBooks());
    }

}
