package ru.otus.spring082022.homework_05.dao;

import ru.otus.spring082022.homework_05.domain.Author;

import java.util.List;

public interface AuthorDAO {
    Author getById(long id);

    List<Author> getAll();

}
