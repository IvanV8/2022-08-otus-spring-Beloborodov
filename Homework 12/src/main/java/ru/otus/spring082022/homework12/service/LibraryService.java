package ru.otus.spring082022.homework12.service;

import ru.otus.spring082022.homework12.domain.Author;
import ru.otus.spring082022.homework12.domain.Book;
import ru.otus.spring082022.homework12.domain.Comment;
import ru.otus.spring082022.homework12.domain.Genre;

import java.util.List;

public interface LibraryService {

    List<Book> listAllBooks();

    void saveComment(Comment comment);

    Book getBookById(long id);

    List<Author> listAllAuthors();

    List<Genre> listAllGenres();

    void deleteBookById(long id);

    List<Comment> listCommentsByBook(long bookId);

    void saveBook(Book book);

    Comment getCommentById(long commentId);

    void deleteCommentById(long commentId);
}
