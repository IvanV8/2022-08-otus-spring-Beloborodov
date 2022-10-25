package ru.otus.spring082022.homework08.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.spring082022.homework08.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {


    void deleteAllByBook(@Param("book_id") long book_id);

    List<Comment> findAllByBookId(@Param("book_id") long book_id);
}
