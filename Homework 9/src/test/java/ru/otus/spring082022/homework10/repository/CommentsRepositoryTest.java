package ru.otus.spring082022.homework10.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring082022.homework09.domain.Comment;
import ru.otus.spring082022.homework09.repositories.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repo для работы с comments")
@DataJpaTest

public class CommentsRepositoryTest {


    private static final long EXISTING_BOOK_COMMENT_ID = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_COMMENT_AUTHOR = "Alexey Yablokov";


    @Autowired
    private CommentRepository repositoryJpa;

    @Autowired
    private TestEntityManager em;


    @DisplayName("возвращать ожидаемый список comment")
    @Test
    void shouldReturnExpectedCommentList() {

        List<Comment> actualCommentList = repositoryJpa.findAllByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentList)
                .extracting(Comment::getUserName)
                .anyMatch(value -> value.matches(EXISTING_BOOK_COMMENT_AUTHOR))
                .anySatisfy(value -> assertThat(value).matches(EXISTING_BOOK_COMMENT_AUTHOR));

    }


}
