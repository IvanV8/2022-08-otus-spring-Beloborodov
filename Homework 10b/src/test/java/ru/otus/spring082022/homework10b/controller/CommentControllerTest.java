package ru.otus.spring082022.homework10b.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework10b.Homework10bApplication;
import ru.otus.spring082022.homework10b.domain.Author;
import ru.otus.spring082022.homework10b.domain.Book;
import ru.otus.spring082022.homework10b.domain.Comment;
import ru.otus.spring082022.homework10b.domain.Genre;
import ru.otus.spring082022.homework10b.service.LibraryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = Homework10bApplication.class)
@WebMvcTest(controllers = CommentsController.class)
public class CommentControllerTest {

    private static final long EXISTING_ID = 1L;
    private static final long EXISTING_BOOK_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Alexander Pushkin";
    private static final String EXISTING_BOOK_TITLE = "Evgeny Onegin";
    private static final long EXISTING_BOOK_GENRE_ID = 1;
    private static final String EXISTING_BOOK_GENRE_NAME = "Roman";

    private static final String EXISTING_COMMENT_USER = "Yablokov";
    private static Book book;
    private static Comment comment;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LibraryService libraryService;

    @BeforeAll
    public static void initMockData() {
        book = new Book(-1, EXISTING_BOOK_TITLE, "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));
        comment = new Comment(-1, EXISTING_COMMENT_USER, LocalDateTime.now(), "comment", book);
    }


    @Test
    @DisplayName("API возвращает список комментов")
    public void shouldReturnCommentsJson() throws Exception {


        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        when(libraryService.listCommentsByBook(EXISTING_ID)).thenReturn(comments);

        mockMvc.perform(get("/api/comments/bybook/" + EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userName", is(EXISTING_COMMENT_USER)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("API удаляет коммент")
    public void shouldDeleteComment() throws Exception {
        mockMvc.perform(delete("/api/comments/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("API сохраняет коммент")
    public void shouldSaveComment() throws Exception {
        mockMvc.perform(post("/api/comments/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().is(200));
    }


}