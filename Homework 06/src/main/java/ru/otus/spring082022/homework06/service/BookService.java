package ru.otus.spring082022.homework06.service;

import ru.otus.spring082022.homework06.domain.Author;
import ru.otus.spring082022.homework06.domain.Book;
import ru.otus.spring082022.homework06.domain.Comment;
import ru.otus.spring082022.homework06.domain.Genre;

import java.util.List;

public interface BookService {

    List<Book> listAllBooks();

    List<Comment> listAllCommentsByBook(long bookId);

    Comment updateComment(long id);

    Book getBookById(long id);

    List<Author> listAllAuthors();

    long newBook();

    void updateBook(long id);

    List<Genre> listAllGenres();

    void deleteBookById(long id);

    Long reportCount();

    long newComment();

    List<Comment> listCommentsByBook(long bookId);


}
