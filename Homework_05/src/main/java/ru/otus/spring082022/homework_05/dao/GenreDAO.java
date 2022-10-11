package ru.otus.spring082022.homework_05.dao;


import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

public interface GenreDAO {
    Genre getById(long id);

    List<Genre> getAll();
}
