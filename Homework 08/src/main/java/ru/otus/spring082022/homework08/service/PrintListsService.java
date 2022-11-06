package ru.otus.spring082022.homework08.service;

import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;

import java.util.List;

public interface PrintListsService {

    void PrintBooks(List<Book> books);

    void PrintComments(List<Comment> comments);

    void PrintAuthors(List<Author> authors);

    void PrintGenres(List<Genre> genre);

}
