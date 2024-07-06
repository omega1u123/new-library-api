package org.example.libraryservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.libraryservice.service.BookCommandService;
import org.example.libraryservice.service.BookQueryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookConsumer {

    private final BookCommandService bookCommandService;

    @KafkaListener(topics = "take_book-topic", groupId = "1")
    public void takeBook(String message) {
        log.info("message from take_book-topic : {}", message);
        bookCommandService.takeBook(Integer.parseInt(message));
    }

    @KafkaListener(topics = "delete_book-topic", groupId = "6")
    public void deleteBook(String message) {
        bookCommandService.deleteBook(Integer.parseInt(message));
    }

    @KafkaListener(topics = "add_book-topic", groupId = "5")
    public void addBook(String message) {
        log.info("message from add_book-topic : {}", message);
        bookCommandService.addBook(Integer.parseInt(message));
    }

    @KafkaListener(topics = "return_book-topic", groupId = "2")
    public void returnBook(String message) {
        log.info("message from return_book-topic : {}", message);
        bookCommandService.returnBook(Integer.parseInt(message));
    }
}
