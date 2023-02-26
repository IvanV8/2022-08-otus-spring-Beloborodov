package ru.otus.spring082022.homework13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring082022.homework13.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    void deleteAllByBookId(long bookId);

    @Query("select c from Comment c where c.book.id = :bookId")
    List<Comment> findAllByBookId(@Param("bookId") long bookId);

    Comment save(Comment Comment);
}
