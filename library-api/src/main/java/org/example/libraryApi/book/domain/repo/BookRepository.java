package org.example.libraryApi.book.domain.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.book.domain.BookMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Book> getAll(){
        try {
            return jdbcTemplate.query("select * from book", new BookMapper());
        }catch (RuntimeException ex){
            log.info("get all repo ex: {}", ex.getMessage());
            return null;
        }
    }

    public Book save(Book book){
        try {
            jdbcTemplate.update("insert into book (isbn, title, genre, description, author) values (?, ?, ?, ?, ?)",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getGenre(),
                    book.getDescription(),
                    book.getAuthor());
            return book;
        }catch (Exception ex){
            log.info("repo save ex: {}", ex.getMessage());
            return null;
        }
    }

    public Book getById(int id){
        return jdbcTemplate.query("select * from book where id=?", new BookMapper(), id)
                .stream().findAny().orElse(null);
    }

    public Book getByIsbn(String isbn){
        return jdbcTemplate.query("select * from book where isbn=?", new BookMapper(), isbn)
                .stream().findAny().orElse(null);
    }


    public boolean removeById(int id){
        int res = jdbcTemplate.update("delete from book where id=?", id);
        log.info("res : {}", res);
        return res == 1;
    }

    public Book update(int id, Book book){
        log.info("updated book: {}", book.toString());
            try {
                jdbcTemplate.update("update book set isbn=?, title=?, genre=?, description=?, author=? where id=?",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getGenre(),
                        book.getDescription(),
                        book.getAuthor(),
                        id);
                book.setId(id);
                return book;
            }catch (Exception ex){
                log.info("update book exception: {}", ex.getMessage());
                return null;
            }
    }

}
