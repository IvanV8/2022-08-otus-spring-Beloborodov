package ru.otus.spring082022.homework09.service;

import ru.otus.spring082022.homework09.domain.Author;
import ru.otus.spring082022.homework09.domain.Book;
import ru.otus.spring082022.homework09.domain.Comment;
import ru.otus.spring082022.homework09.domain.Genre;

import java.util.List;

public interface LibraryService {

    List<Book> listAllBooks();

    void saveComment(Comment comment, long bookId);

    Book getBookById(long id);

    List<Author> listAllAuthors();

    List<Genre> listAllGenres();

    void deleteBookById(long id);

    List<Comment> listCommentsByBook(long bookId);

    void saveBook(Book book);

    Comment getCommentById(long commentId);

    void deleteCommentById(long commentId);
}
