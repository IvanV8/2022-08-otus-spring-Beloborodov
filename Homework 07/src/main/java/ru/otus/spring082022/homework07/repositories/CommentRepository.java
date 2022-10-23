package ru.otus.spring082022.homework07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring082022.homework07.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Query("delete from Comment c where c.book.id = :book_id")
    void deleteAllByBook(@Param("book_id") long book_id);

    @Query("select c from Comment c where c.book.id = :book_id")
    List<Comment> findAllByBookId(@Param("book_id") long book_id);
}
