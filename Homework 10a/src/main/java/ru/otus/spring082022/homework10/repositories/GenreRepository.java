package ru.otus.spring082022.homework10.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring082022.homework10.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
