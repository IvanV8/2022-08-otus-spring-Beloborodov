package ru.otus.spring082022.homework_05.service;

import ru.otus.spring082022.homework_05.domain.Author;
import ru.otus.spring082022.homework_05.domain.Book;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

public interface BookService {

    List<Book> listAllBooks();

    List<Author> listAllAuthors();

    long newBook();

    void updateBook(long id);

    List<Genre> listAllGenres();

    void delete(long id);

    int reportCount();
}
