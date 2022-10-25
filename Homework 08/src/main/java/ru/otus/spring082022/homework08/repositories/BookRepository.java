package ru.otus.spring082022.homework08.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.otus.spring082022.homework08.domain.Book;

import java.util.List;
import java.util.Optional;



public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Optional<Book> findById(long id);

    Book save(Book book);
}



