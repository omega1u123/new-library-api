package org.example.libraryApi.book.domain.repo;

import org.example.libraryApi.book.domain.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("select * from book")
    List<Book> getAll();

    @Query("select * from book where id=:id")
    Book getById(int id);

    @Query("select * from book where isbn=:isbn")
    Book getByIsbn(String isbn);

    @Modifying
    @Query("delete from book where id=:id")
    boolean removeById(int id);

    @Modifying
    @Query("update book set isbn=:isbn, title=:title, genre=:genre, description=:description, author=:author where id=:id")
    Book updateBook(int id, Book book);

}
