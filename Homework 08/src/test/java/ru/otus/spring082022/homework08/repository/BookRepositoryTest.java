package ru.otus.spring082022.homework08.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepository  should ")
class BookRepositoryTest extends AbstractRepositoryTest {

    private static final String NEW_BOOK_TITLE = "Knight Silver";
    private static final String NEW_BOOK_ISBN = "324-435345-345";
    private static final String EXISTING_BOOK_AUTHOR_NAME = "A.Pushkin";
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";

    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" возвращать корректный список книг")
    @Test
    void shouldReturnCorrectBookList() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(3);

    }

    @DisplayName("должен корректно сохранять книгу ")
    @Test
    void shouldCorrectSaveBook() {
        val book = new Book(NEW_BOOK_TITLE, NEW_BOOK_ISBN, new Author(EXISTING_BOOK_AUTHOR_NAME), new Genre(EXISTING_BOOK_GENRE_NAME));
        val actual = bookRepository.save(book);
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getTitle()).isEqualTo(book.getTitle());
    }

    @DisplayName("должен  удалять книгу")
    @Test
    void shouldReturnCorrectDeleteBook() {
        val book = bookRepository.findAll().get(0);
        bookRepository.deleteById(book.getId());
        assertThat(bookRepository.findById(book.getId())).isEmpty();
    }
}
