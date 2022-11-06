package ru.otus.spring082022.homework08.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring082022.homework08.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();


    @Query("{ '_id' : ?0 }")
    Optional<Book> findById(String id);

    Book save(Book book);
}



