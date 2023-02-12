package ru.otus.spring082022.homework13.service;

import ru.otus.spring082022.homework13.domain.*;

import java.util.List;

public interface LibraryService {

    List<Book> listAllBooks();

    void saveComment(Comment comment, LibraryUser userDetails);

    Book getBookById(long id);

    List<Author> listAllAuthors();

    List<Genre> listAllGenres();

    void deleteBookById(long id);

    List<Comment> listCommentsByBook(long bookId);

    void saveBook(Book book);

    Comment getCommentById(long commentId);

    void deleteCommentById(long commentId);
}
