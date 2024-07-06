package org.example.libraryservice.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        var book = new Book();
        book.setId(rs.getInt("id"));
        log.info("BookMapper mapRow() call");
        try {
            log.info("getDate != null, {}", rs.getDate("taken_date").toLocalDate());
            book.setTakenDate((rs.getDate("taken_date").toLocalDate()));
            book.setReturnDate(rs.getDate("return_date").toLocalDate());
            return book;
        } catch (NullPointerException ex) {
            log.info("BookMapper nullPointer ex");
            book.setTakenDate(null);
            book.setReturnDate(null);
            return book;
        }

    }
}