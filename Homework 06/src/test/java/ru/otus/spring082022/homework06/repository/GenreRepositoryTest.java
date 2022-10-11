package ru.otus.spring082022.homework06.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring082022.homework06.domain.Genre;
import ru.otus.spring082022.homework06.repositories.GenreRepositoryImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repo для работы с genre")
@DataJpaTest
@Import(GenreRepositoryImpl.class)

public class GenreRepositoryTest {


    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";


    @Autowired
    private GenreRepositoryImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {


        val actualGenre = repositoryJpa.getById(EXISTING_BOOK_GENRE_ID);
        val expectedGenre = em.find(Genre.class, EXISTING_BOOK_GENRE_ID);
        assertThat(actualGenre).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedGenre);
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenreList() {

        Genre expectedGenre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);

        List<Genre> actualGenreList = repositoryJpa.getAll();
        assertThat(actualGenreList)
                .contains(expectedGenre);
    }


}
