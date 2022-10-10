package ru.otus.spring082022.homework06.repositories;


import ru.otus.spring082022.homework06.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> getAll();

    Optional<Genre> getById(long id);
}
