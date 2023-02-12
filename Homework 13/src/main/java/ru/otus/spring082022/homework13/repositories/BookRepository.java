package ru.otus.spring082022.homework13.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework13.domain.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    @EntityGraph(value = "book-author-genre-graph",
            type = org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();

    @PostFilter("hasPermission(filterObject, 'READ')")
    @EntityGraph(value = "book-author-genre-graph",
            type = org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD)
    Optional<Book> findById(long id);

    @Override
    @PreAuthorize("hasPermission(#Book, 'WRITE')")
    Book save(@Param("Book") Book book);
}



