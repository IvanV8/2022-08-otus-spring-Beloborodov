package ru.otus.spring082022.homework10b.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework10b.Homework10bApplication;
import ru.otus.spring082022.homework10b.service.LibraryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(classes = Homework10bApplication.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    private static final long EXISTING_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LibraryService libraryService;

    @Test
    @DisplayName("API возвращает список книг")
    public void shouldReturnBooksJson() throws Exception {
        mockMvc.perform(
                        get("/api/books")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("API удаляет книгу")
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(
                        delete("/api/delete-book/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}