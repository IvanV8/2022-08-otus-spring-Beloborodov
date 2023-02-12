package ru.otus.spring082022.homework13.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring082022.homework13.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @PreAuthorize("hasRole('ADMIN')")
    void deleteAllByBookId(long bookId);

    @PostFilter("hasPermission(filterObject, 'READ')")
    @Query("select c from Comment c where c.book.id = :bookId")
    List<Comment> findAllByBookId(@Param("bookId") long bookId);

    @Override
    @PreAuthorize("hasPermission(#Comment, 'WRITE')")
    Comment save(@Param("Comment") Comment Comment);
}
