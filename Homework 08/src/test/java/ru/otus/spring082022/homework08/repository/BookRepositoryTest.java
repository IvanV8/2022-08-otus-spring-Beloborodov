package ru.otus.spring082022.homework08.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.BookRepository;
import ru.otus.spring082022.homework08.repositories.CommentRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepository  should ")
@ComponentScan("ru.otus.spring082022.homework08.events")
class BookRepositoryTest extends AbstractRepositoryTest {

    private static final String NEW_BOOK_TITLE = "Knight Silver";
    private static final String NEW_BOOK_ISBN = "324-435345-345";
    private static final String EXISTING_BOOK_AUTHOR_NAME = "A.Pushkin";
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";

    private static final String EXISTING_AUTHOR_COMMENT = "Alexey Yablokov";


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName(" возвращать корректный список книг")
    @Test
    void shouldReturnCorrectBookList() {
        val books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(4);

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
        assertThat(commentRepository.findAllByBookId(book.getId())).isEmpty();
    }


    @DisplayName("должен корректно сохранять книгу каскадно ")
    @Test
    void shouldCorrectSaveCascadeBook() {
        val book = new Book(NEW_BOOK_TITLE, NEW_BOOK_ISBN, new Author(EXISTING_BOOK_AUTHOR_NAME), new Genre(EXISTING_BOOK_GENRE_NAME));
        val actual_book = bookRepository.save(book);
        val comment = new Comment(EXISTING_AUTHOR_COMMENT, LocalDateTime.now(), "Great", book);
        val actual_comment = commentRepository.save(comment);
        Author new_author = new Author("M.Prishvin");
        book.setAuthor(new_author);
        bookRepository.saveBookEverywhere(book);
        Comment new_comment = commentRepository.findById(actual_comment.getId()).orElse(null);
        assertThat(new_comment).isNotNull();
        assertThat(new_comment.getBook().getAuthor()).isEqualTo(new_author);
    }

}
