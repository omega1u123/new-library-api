package org.example.libraryservice.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getInt("id"),
                rs.getDate("taken_date").toLocalDate(),
                rs.getDate("return_date").toLocalDate()
        );
    }
}