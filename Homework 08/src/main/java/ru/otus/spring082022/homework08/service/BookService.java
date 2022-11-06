package ru.otus.spring082022.homework08.service;

import ru.otus.spring082022.homework08.domain.Comment;

public interface BookService {

    void listAllBooks();

    Comment updateComment(String id);

    void listAllAuthors();

    void newBook();

    void updateBook(String id);

    void listAllGenres();

    void deleteBookById(String id);

    void deleteBook(String id);

    String reportCount();

    void newComment();

    void listCommentsByBook(String bookId);


}
