package ru.otus.spring082022.homework13.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework13.domain.*;
import ru.otus.spring082022.homework13.service.LibraryService;
import ru.otus.spring082022.homework13.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = CommentsController.class)
@AutoConfigureMockMvc(addFilters = true)
public class CommentControllerTest {

    private static final long EXISTING_ID = 1L;
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";
    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";

    private static final String EXISTING_COMMENT_USER = "testuser";
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
    @DisplayName("API возвращает список комментов")
    public void shouldReturnCommentsJson() throws Exception {


        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        when(libraryService.listCommentsByBook(EXISTING_ID)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/bybook/" + EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userName.login", is(EXISTING_COMMENT_USER)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("API возвращает список комментов 403")
    public void shouldReturnComments403() throws Exception {

        mockMvc.perform(get("/api/comments/bybook/" + EXISTING_ID))
                .andExpect(status().isForbidden());
        ;

    }

    @Test
    @DisplayName("API удаляет коммент")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldDeleteComment() throws Exception {
        mockMvc.perform(delete("/api/comments/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("API удаляет коммент 403")
    public void shouldDeleteComment403() throws Exception {
        mockMvc.perform(delete("/api/comments/" + EXISTING_ID)).andExpect(status().isForbidden());
    }


    @Test
    @DisplayName("API сохраняет коммент")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void shouldSaveComment() throws Exception {
        mockMvc.perform(post("/api/comments/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().is(200));
    }

    @Test
    @DisplayName("API сохраняет коммент 403")
    public void shouldSaveCommentreturn403() throws Exception {
        mockMvc.perform(post("/api/comments/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("API сохраняет коммент нет прав")
    @WithMockUser(username = "child", roles = {"CHILD"})
    public void shouldSaveCommentNoAuthoritites() throws Exception {
        mockMvc.perform(post("/api/comments/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isForbidden());
    }

}