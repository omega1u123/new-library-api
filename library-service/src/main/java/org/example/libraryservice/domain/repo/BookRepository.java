package org.example.libraryservice.domain.repo;

import org.example.libraryservice.domain.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Modifying
    @Query("insert into book (id, taken_date, return_date) values (:id, null, null)")
    int addBook(int id);

    @Modifying
    @Query("update book set taken_date=:takenDate, return_date=:returnDate where id=:id")
    int takeBook(int id, LocalDate takenDate, LocalDate returnDate);

    @Modifying
    @Query("update book set taken_date=null, return_date=null where id=:id")
    int returnBook(int id);

    @Modifying
    @Query("delete from book where id=:id")
    int deleteBook(int id);

    @Query("select * from book where taken_date is null ")
    List<Book> getFreeBooks();

}
