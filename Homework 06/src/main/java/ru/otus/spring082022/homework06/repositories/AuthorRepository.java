package ru.otus.spring082022.homework06.repositories;

import ru.otus.spring082022.homework06.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAll();

    Optional<Author> getById(long id);
}
