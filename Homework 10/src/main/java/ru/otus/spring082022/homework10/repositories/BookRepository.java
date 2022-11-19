package ru.otus.spring082022.homework10.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework10.domain.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre-graph",
            type = org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();

    Optional<Book> findById(long id);

    @Override
    Book save(Book book);
}



