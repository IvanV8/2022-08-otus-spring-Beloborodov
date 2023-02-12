package ru.otus.spring082022.homework13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring082022.homework13.domain.Genre;
import ru.otus.spring082022.homework13.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repo для работы с genre")
@DataJpaTest


public class GenreRepositoryTest {


    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";


    @Autowired
    private GenreRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {


        Optional<Genre> genre = repositoryJpa.findById(EXISTING_BOOK_GENRE_ID);
        assertThat(genre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", EXISTING_BOOK_GENRE_NAME);
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenreList() {

        Genre expectedGenre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);

        List<Genre> actualGenreList = repositoryJpa.findAll();
        assertThat(actualGenreList.stream().anyMatch(s -> s.getName().equals(EXISTING_BOOK_GENRE_NAME)));
    }


}
