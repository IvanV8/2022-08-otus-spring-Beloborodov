package ru.otus.spring082022.homework07.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring082022.homework07.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
