package ru.otus.spring082022.homework10b.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring082022.homework10b.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
