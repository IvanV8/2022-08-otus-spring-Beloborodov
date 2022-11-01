package ru.otus.spring082022.homework_05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с genre")
@JdbcTest
@Import(GenreDAOJdbc.class)

public class GenreDAOJdbcTest {


    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";


    @Autowired
    private GenreDAO GenreDao;

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {

        Genre expectedGenre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
        Genre actualGenre = GenreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }


    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenreList() {

        Genre expectedGenre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);

        List<Genre> actualGenreList = GenreDao.getAll();
        assertThat(actualGenreList)
                .contains(expectedGenre);
    }


}
