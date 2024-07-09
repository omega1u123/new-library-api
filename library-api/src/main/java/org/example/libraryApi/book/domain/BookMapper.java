package org.example.libraryApi.book.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getInt("id"),
                rs.getString("isbn"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getString("description"),
                rs.getString("author"));
    }
}
