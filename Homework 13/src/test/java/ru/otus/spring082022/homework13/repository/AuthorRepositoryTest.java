package ru.otus.spring082022.homework13.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring082022.homework13.domain.Author;
import ru.otus.spring082022.homework13.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repo для работы с author")
@DataJpaTest
public class AuthorRepositoryTest {


    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";


    @Autowired
    private AuthorRepository repositoryJpa;


    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Optional<Author> author = repositoryJpa.findById(EXISTING_BOOK_AUTHOR_ID);
        assertThat(author).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", EXISTING_BOOK_AUTHOR_NAME);

    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorList() {

        List<Author> actualAuthorList = repositoryJpa.findAll();
        assertThat(actualAuthorList.stream().anyMatch(s -> s.getName().equals(EXISTING_BOOK_AUTHOR_NAME)));

    }


}
