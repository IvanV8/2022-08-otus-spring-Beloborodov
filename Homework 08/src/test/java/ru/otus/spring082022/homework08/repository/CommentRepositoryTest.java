package ru.otus.spring082022.homework08.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.CommentRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CommentRepository  should ")
class CommentRepositoryTest extends AbstractRepositoryTest {

    private static final String EXISTING_AUTHOR_COMMENT = "Alexey Yablokov";
    private static final String NEW_BOOK_TITLE = "Knight Silver";
    private static final String NEW_BOOK_ISBN = "324-435345-345";
    private static final String EXISTING_BOOK_AUTHOR_NAME = "A.Pushkin";
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName(" возвращать корректный список комментариев")
    @Test
    void shouldReturnCorrectBookList() {
        val comments = commentRepository.findAll();
        assertThat(comments).isNotNull().hasSize(1);
        assertThat(comments.stream().anyMatch(s -> s.getUserName().equals(EXISTING_AUTHOR_COMMENT)));

    }

    @DisplayName("должен корректно сохранять комментарий ")
    @Test
    void shouldCorrectSaveComment() {
        val comment = new Comment(EXISTING_AUTHOR_COMMENT, LocalDateTime.now(), "Great",
                new Book(NEW_BOOK_TITLE, NEW_BOOK_ISBN, new Author(EXISTING_BOOK_AUTHOR_NAME), new Genre(EXISTING_BOOK_GENRE_NAME)));
        val actual = commentRepository.save(comment);
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getUserName()).isEqualTo(comment.getUserName());
    }

    @DisplayName("должен  удалять книгу")
    @Test
    void shouldReturnCorrectDeleteComment() {
        val comment = commentRepository.findAll().get(0);
        commentRepository.deleteById(comment.getId());
        assertThat(commentRepository.findById(comment.getId())).isEmpty();
    }
}
