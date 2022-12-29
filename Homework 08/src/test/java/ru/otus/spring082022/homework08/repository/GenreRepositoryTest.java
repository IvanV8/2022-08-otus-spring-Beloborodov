package ru.otus.spring082022.homework08.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring082022.homework08.repositories.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AuthorRepository  should ")
class GenreRepositoryTest extends AbstractRepositoryTest {

    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" содержать элемент с именем Pushkin")
    @Test
    void shouldReturnCorrectAuthorList() {
        val genres = genreRepository.findAll();
        assertThat(genres).isNotNull().hasSize(3);
        assertThat(genres.stream().anyMatch(s -> s.getName().equals(EXISTING_BOOK_GENRE_NAME)));

    }
}
