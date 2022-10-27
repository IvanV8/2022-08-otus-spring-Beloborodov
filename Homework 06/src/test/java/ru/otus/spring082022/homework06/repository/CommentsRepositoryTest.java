package ru.otus.spring082022.homework06.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring082022.homework06.domain.Comment;
import ru.otus.spring082022.homework06.repositories.CommentRepositoryImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repo для работы с comments")
@DataJpaTest
@Import(CommentRepositoryImpl.class)

public class CommentsRepositoryTest {


    private static final long EXISTING_BOOK_COMMENT_ID = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_COMMENT_AUTHOR = "Alexey Yablokov";


    @Autowired
    private CommentRepositoryImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый comment по его id")
    @Test
    void shouldReturnExpectedCommentById() {


        val actualComment = repositoryJpa.getById(EXISTING_BOOK_COMMENT_ID);
        val expectedComment = em.find(Comment.class, EXISTING_BOOK_COMMENT_ID);
        assertThat(actualComment).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedComment);
    }


    @DisplayName("возвращать ожидаемый список comment")
    @Test
    void shouldReturnExpectedCommentList() {

        List<Comment> actualCommentList = repositoryJpa.getAllByBook(EXISTING_BOOK_ID);
        assertThat(actualCommentList)
                .extracting(Comment::getUserName)
                .anyMatch(value -> value.matches(EXISTING_BOOK_COMMENT_AUTHOR))
                .anySatisfy(value -> assertThat(value).matches(EXISTING_BOOK_COMMENT_AUTHOR));

    }


}
