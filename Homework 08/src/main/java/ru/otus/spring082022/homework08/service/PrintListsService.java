package ru.otus.spring082022.homework08.service;

import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;

import java.util.List;

public interface PrintListsService {

    void printBooks(List<Book> books);

    void printComments(List<Comment> comments);

    void printAuthors(List<Author> authors);

    void printGenres(List<Genre> genre);

}
