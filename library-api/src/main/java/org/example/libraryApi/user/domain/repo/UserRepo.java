package org.example.libraryApi.user.domain.repo;

import org.example.libraryApi.book.domain.Book;
import org.example.libraryApi.user.domain.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("select * from user_table where username=:username")
    User getByUsername(String username);

}
