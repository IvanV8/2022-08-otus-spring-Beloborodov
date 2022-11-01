package ru.otus.spring082022.homework06.repositories;

import ru.otus.spring082022.homework06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> getById(long id);

    Comment save(Comment comment);

    List<Comment> getAllByBook(long bookId);

    void deleteById(long id);

    void deleteAllByBook(long bookId);

}
