package ru.otus.spring082022.homework13.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework13.domain.Genre;
import ru.otus.spring082022.homework13.service.LibraryService;
import ru.otus.spring082022.homework13.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = GenreController.class)

public class GenresControllerTest {

    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Poem";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserServiceImpl userService;


    @Test
    @WithMockUser(username = "test_user")
    @DisplayName("API возвращает список жанров")
    public void shouldReturnGenresJson() throws Exception {


        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));
        when(libraryService.listAllGenres()).thenReturn(genres);

        mockMvc.perform(get("/api/genres").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(EXISTING_BOOK_GENRE_NAME)));


    }

    @Test
    @DisplayName("API список жанров 403")
    public void shouldReturn403Genres() throws Exception {

        mockMvc.perform(get("/api/genres").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

}