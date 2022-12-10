package ru.otus.spring082022.homework10.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring082022.homework09.domain.Author;
import ru.otus.spring082022.homework09.domain.Book;
import ru.otus.spring082022.homework09.domain.Genre;
import ru.otus.spring082022.homework09.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с book")
@DataJpaTest

public class BookRepositoryCustomTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";

    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final long EXISTING_BOOK_GENRE_ID = 2;
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";


    @Autowired
    private BookRepository bookRepository;


    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        Long actualPersonsCount = bookRepository.count();
        assertThat(actualPersonsCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Genre genre = new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
        Author author = new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
        Book expectedBook = new Book(0, "Fear and Loathing in Las Vegas", "2131234-34-33", author, genre);
        val actualBook = bookRepository.save(expectedBook);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);

    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {

        Optional<Book> book = bookRepository.findById(EXISTING_BOOK_ID);
        assertThat(book).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("title", EXISTING_BOOK_TITLE);
    }


    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksist() {


        List<Book> actualBookList = bookRepository.findAll();

        assertThat(actualBookList.size())
                .isEqualTo(2);
    }

    @DisplayName("удалять книгу по  id")
    @Test
    void shouldCorrectDeleteBookById() {


        bookRepository.deleteById(EXISTING_BOOK_ID);
        assertThat(bookRepository.findById(EXISTING_BOOK_ID)).isEmpty();
    }
}
