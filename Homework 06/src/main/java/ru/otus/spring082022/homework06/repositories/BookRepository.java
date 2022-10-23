package ru.otus.spring082022.homework06.repositories;

import ru.otus.spring082022.homework06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Long count();

    Optional<Book> getById(long id);

    Book save(Book book);

    List<Book> getAll();


    void deleteById(long id);


}
