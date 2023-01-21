package ru.otus.spring082022.homework08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring082022.homework08.domain.Author;


public interface AuthorRepository extends MongoRepository<Author, String> {

}
