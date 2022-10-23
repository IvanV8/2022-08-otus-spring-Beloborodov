package ru.otus.spring082022.homework_05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring082022.homework_05.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Dao для работы с book")
@JdbcTest
@Import(AuthorDAOJdbc.class)

public class AuthorDAOJdbcTest {


    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";


    @Autowired
    private AuthorDAO authorDao;

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {

        Author expectedAuthor = new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
        Author actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }


    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorList() {

        Author expectedAuthor = new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);

        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList)
                .contains(expectedAuthor);
    }


}
