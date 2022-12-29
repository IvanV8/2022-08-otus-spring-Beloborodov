package ru.otus.spring082022.homework08.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring082022.homework08.domain.Book;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {


}



