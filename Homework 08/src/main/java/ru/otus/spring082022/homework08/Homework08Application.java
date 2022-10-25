package ru.otus.spring082022.homework08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.AuthorRepository;
import ru.otus.spring082022.homework08.repositories.BookRepository;
import ru.otus.spring082022.homework08.repositories.GenreRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableMongoRepositories
public class Homework08Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework08Application.class, args);
    }


    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @PostConstruct
    public void init() {
        bookRepository.save(new Book(1, "Evgeniy Onegin","324-234324-234",
                new Author(1,"Pushkin"),
                new Genre(1,"Poem")));
    }
}
