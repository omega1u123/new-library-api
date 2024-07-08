package org.example.libraryservice.domain.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryservice.domain.Book;
import org.example.libraryservice.domain.BookMapper;
import org.example.libraryservice.exception.*;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public Book getById(int id) {
        return jdbcTemplate.query("select * from book where id=?", new BookMapper(), id)
                .stream().findAny().orElseThrow();
    }

    public int addBook(int id) {
        try {
            jdbcTemplate.update("insert into book (id) values (?)",
                    id
            );
            return 1;
        } catch (Exception ex) {
            log.info("repo addBook ex: {}", ex.getMessage());
            throw new BookNotSavedDbException();
        }
    }

    public Book takeBook(Book book) {
        try {
            jdbcTemplate.update("update book set taken_date=?, return_date=? where id=?",
                    book.getTakenDate(),
                    book.getReturnDate(),
                    book.getId()
            );
            return book;
        } catch (Exception ex) {
            log.info("repo takeBook ex: {}", ex.getMessage());
            throw new BookNotTakenDbException();
        }
    }

    public int returnBook(int id) {
        try {
            jdbcTemplate.update("update book set taken_date=?, return_date=? where id=?",
                    null,
                    null,
                    id
            );
            return 2;
        } catch (Exception ex) {
            log.info("repo returnBook ex: {}", ex.getMessage());
            throw new BookNotReturnedDbException();
        }
    }

    public int deleteBook(int id) {
        try {
            jdbcTemplate.update("delete from book where id=:?",
                    id
            );
            return 3;
        } catch (Exception ex) {
            log.info("repo deleteBook ex: {}", ex.getMessage());
            throw new BookNotDeletedDbException();
        }
    }

    public List<Integer> getFreeBooks() {
        try {
            return jdbcTemplate.queryForList("select id from book where taken_date is null ",
                    Integer.class);
        } catch (Exception ex) {
            log.info("repo getFreeBooks ex: {}", ex.getMessage());
            throw new BookNotFoundDbException();
        }
    }

}
