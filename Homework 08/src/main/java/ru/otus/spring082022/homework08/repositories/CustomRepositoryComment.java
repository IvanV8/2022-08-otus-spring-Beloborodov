package ru.otus.spring082022.homework08.repositories;

import ru.otus.spring082022.homework08.domain.Comment;

import java.util.List;

public interface CustomRepositoryComment {
    List<Comment> findAllByBookId(String bookId);
}
