package ru.otus.spring082022.homework08.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring082022.homework08.repositories.AuthorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AuthorRepository  should ")
class AuthorRepositoryTest extends AbstractRepositoryTest {

    private static final String EXISTING_BOOK_AUTHOR_NAME = "A.Pushkin";
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName(" содержать элемент с именем Pushkin")
    @Test
    void shouldReturnCorrectAuthorList() {
        val authors = authorRepository.findAll();
        assertThat(authors).isNotNull().hasSize(3);
        assertEquals(authors.get(0).getName(), EXISTING_BOOK_AUTHOR_NAME);

    }
}
