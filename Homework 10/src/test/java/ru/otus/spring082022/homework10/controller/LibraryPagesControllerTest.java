package ru.otus.spring082022.homework10.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework10.Homework10Application;
import ru.otus.spring082022.homework10.domain.Author;
import ru.otus.spring082022.homework10.domain.Genre;
import ru.otus.spring082022.homework10.dto.BookDto;
import ru.otus.spring082022.homework10.dto.CommentDto;
import ru.otus.spring082022.homework10.pages.LibraryPagesController;
import ru.otus.spring082022.homework10.service.LibraryService;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = Homework10Application.class)
@WebMvcTest(controllers = LibraryPagesController.class)
public class LibraryPagesControllerTest {

    private static final long EXISTING_ID = 1L;
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LibraryService libraryService;

    @Test
    @DisplayName("API сохраняет книгу")
    public void shouldSaveBook() throws Exception {
        BookDto book = new BookDto(-1, "Title", "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));

        mockMvc.perform(
                        post("/save-book").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().is(302));

    }

    @Test
    @DisplayName("API сохраняет коммент")
    public void shouldSaveComment() throws Exception {

        CommentDto comment = new CommentDto(-1, "user", LocalDateTime.now(), "comment", 1);

        mockMvc.perform(
                        post("/save-comment/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().is(302));

    }

    @Test
    @DisplayName("API удаляет книгу")
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(
                        delete("/delete-book/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}