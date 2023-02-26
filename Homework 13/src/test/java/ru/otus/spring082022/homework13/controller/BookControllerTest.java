package ru.otus.spring082022.homework13.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework13.domain.*;
import ru.otus.spring082022.homework13.dto.BookDto;
import ru.otus.spring082022.homework13.service.LibraryService;
import ru.otus.spring082022.homework13.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = BookController.class)

public class BookControllerTest {

    private static final long EXISTING_ID = 1L;
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";
    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";
    private static Book book;
    private static Comment comment;
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserServiceImpl userService;


    @BeforeAll
    public static void initMockData() {
        LibraryUser user = new LibraryUser(1, "testuser", "password", "test", "test",
                new UserRole(1L, "ADMIN"));


        book = new Book(-1, EXISTING_BOOK_TITLE, "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));
        comment = new Comment(-1, user, LocalDateTime.now(), "comment", book);


    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("API возвращает список книг")
    public void shouldReturnBooksJson() throws Exception {

        mockMvc.perform(get("/api/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> books = new ArrayList<>();
        books.add(book);
        when(libraryService.listAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is(EXISTING_BOOK_TITLE)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("API список книг 403")
    public void shouldReturn401Books() throws Exception {

        mockMvc.perform(get("/api/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    @DisplayName("API удаляет книгу")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));
        mockMvc.perform(delete("/api/books/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("API удаляения книг 403")
    public void shouldDeleteBook401() throws Exception {
        mockMvc.perform(delete("/api/books/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("API Сохранить книгу")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldSaveBook() throws Exception {
        BookDto book = new BookDto(-1, "Title", "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("API Сохранить книгу 403")
    public void shouldSaveBook401() throws Exception {
        BookDto book = new BookDto(-1, "Title", "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("API Сохранить книгу нет доступа")
    public void shouldSaveBookNoAuthorities() throws Exception {
        BookDto book = new BookDto(-1, "Test", "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                        .with(user("child").roles("CHILD"))
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isForbidden());
    }
}