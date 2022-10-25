package ru.otus.spring082022.homework08.repositories;



import org.springframework.data.repository.CrudRepository;
import ru.otus.spring082022.homework08.domain.Genre;


public interface GenreRepository extends CrudRepository<Genre, Long> {

}
