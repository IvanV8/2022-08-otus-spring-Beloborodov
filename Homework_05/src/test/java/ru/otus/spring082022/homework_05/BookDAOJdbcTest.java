package ru.otus.spring082022.homework_05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.spring082022.homework_05.dao.*;
import ru.otus.spring082022.homework_05.domain.Author;
import ru.otus.spring082022.homework_05.domain.Book;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Dao для работы с book")
@JdbcTest
@Import({BookDAOJdbc.class, AuthorDAOJdbc.class, GenreDAOJdbc.class})

public class BookDAOJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";
    private static final String EXISTING_BOOK_ISBN = "978-5-04-116656-4";
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final long EXISTING_BOOK_GENRE_ID = 2;


    @Autowired
    private BookDAO bookDao;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private GenreDAO genreDAO;


    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualPersonsCount = bookDao.count();
        assertThat(actualPersonsCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять пёрсона в БД")
    @Test
    void shouldInsertBook() {
        Genre genre = genreDAO.getById(EXISTING_BOOK_GENRE_ID);
        Author author = authorDAO.getById(EXISTING_BOOK_AUTHOR_ID);
        Book expectedBook = new Book(0, "Fear and Loathing in Las Vegas", "2131234-34-33", author, genre);
        long id = bookDao.insert(expectedBook);

        Book actualBook = bookDao.getById(id);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {
        Genre genre = genreDAO.getById(EXISTING_BOOK_GENRE_ID);
        Author author = authorDAO.getById(EXISTING_BOOK_AUTHOR_ID);
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_ISBN, author, genre);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksist() {
        Genre genre = genreDAO.getById(EXISTING_BOOK_GENRE_ID);
        Author author = authorDAO.getById(EXISTING_BOOK_AUTHOR_ID);
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_BOOK_ISBN, author, genre);

        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }

    @DisplayName("удалять книгу по  id")
    @Test
    void shouldCorrectDeleteBookById() {


        bookDao.deleteById(EXISTING_BOOK_ID);
        assertNull(bookDao.getById(EXISTING_BOOK_ID));
    }
}
