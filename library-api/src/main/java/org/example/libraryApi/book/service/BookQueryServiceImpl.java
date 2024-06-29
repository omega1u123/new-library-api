package org.example.libraryApi.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.repo.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final ConcurrentHashMap<String, List<Integer>> response = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book getBookById(int id) {
        var book = bookRepository.getById(id);
        log.info("book from db: {}", book.toString());
        return book;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        var book = bookRepository.getByIsbn(isbn);
        if(book != null)
            modelMapper.map(book, Book.class);
        return null;
    }

    @Override
    public List<Book> getAvailableBooks() throws InterruptedException {

        String reqId = UUID.randomUUID().toString();

        ProducerRecord<String, String> record = new ProducerRecord<>("available_books-topic", "");
        record.headers().add("reqId", reqId.getBytes());

        kafkaTemplate.send(record);

        while (!response.containsKey(reqId)){
            Thread.sleep(100);
        }

        if(response.get(reqId).get(0) == -1)
            return null;

        List<Book> books = new ArrayList<>();

        for(Integer bookId : response.get(reqId)){
            books.add(getBookById(bookId));
        }

        response.remove(reqId);

        return books;
    }

    @KafkaListener(topics = "available_books_resp-topic", groupId = "4")
    public void listen(ConsumerRecord<String, String> record){
        String reqId = new String(record.headers().lastHeader("reqId").value());
        try {
            List<Integer> books = Stream.of(record.value().split(",")).map(Integer::parseInt).toList();
            log.info("message from available_books_resp-topic : {}", books);
            response.put(reqId, books);
        }catch (NumberFormatException ex){
            response.put(reqId, List.of(-1));
        }
    }
}
