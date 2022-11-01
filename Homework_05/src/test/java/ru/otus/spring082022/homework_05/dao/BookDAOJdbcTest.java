package ru.otus.spring082022.homework_05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring082022.homework_05.domain.Author;
import ru.otus.spring082022.homework_05.domain.Book;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Dao для работы с author")
@JdbcTest
@Import(BookDAOJdbc.class)

public class BookDAOJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";
    private static final String EXISTING_BOOK_ISBN = "978-5-04-116656-4";
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final long EXISTING_BOOK_GENRE_ID = 2;
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";


    @Autowired
    private BookDAO bookDao;

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualPersonsCount = bookDao.count();
        assertThat(actualPersonsCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Genre genre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
        Author author = new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
        Book expectedBook = new Book(0, "Fear and Loathing in Las Vegas", "2131234-34-33", author, genre);
        long id = bookDao.insert(expectedBook);

        Book actualBook = bookDao.getById(id);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {

        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_ISBN,
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksist() {

        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_ISBN,
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));

        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .contains(expectedBook);
    }

    @DisplayName("удалять книгу по  id")
    @Test
    void shouldCorrectDeleteBookById() {


        bookDao.deleteById(EXISTING_BOOK_ID);
        assertNull(bookDao.getById(EXISTING_BOOK_ID));
    }
}
