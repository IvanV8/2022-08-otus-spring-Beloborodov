package ru.otus.spring082022.homework09.pages;


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
import ru.otus.spring082022.homework09.Homework09Application;
import ru.otus.spring082022.homework09.domain.Author;
import ru.otus.spring082022.homework09.domain.Book;
import ru.otus.spring082022.homework09.domain.Comment;
import ru.otus.spring082022.homework09.domain.Genre;
import ru.otus.spring082022.homework09.dto.BookDto;
import ru.otus.spring082022.homework09.dto.CommentDto;
import ru.otus.spring082022.homework09.service.LibraryService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration(classes = Homework09Application.class)
@WebMvcTest(controllers = LibraryPagesController.class)

public class LibraryPagesControllerTest {

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

    @BeforeAll
    public static void initMockData() {
        book = new Book(-1, EXISTING_BOOK_TITLE, "ISBN",
                new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME),
                new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME));
        comment = new Comment(-1, "user", LocalDateTime.now(), "comment", book);
    }

    @Test
    @DisplayName("Получить страницу с книгами")
    public void shouldReturnLibraryPage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(model().attribute("books", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить страницу с редактированием новой книги")
    public void shouldReturnNewBookPage() throws Exception {

        mockMvc.perform(get("/new-book"))
                .andExpect(view().name("book-edit"))
                .andExpect(model().attribute("book", notNullValue()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Получить страницу с редактированием  книги")
    public void shouldReturnEditBookPage() throws Exception {

        when(libraryService.getBookById(EXISTING_ID)).thenReturn(book);

        mockMvc.perform(get("/edit-book/" + EXISTING_ID))
                .andExpect(view().name("book-edit"))
                .andExpect(model().attribute("book", notNullValue()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Сохранить книгу")
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
    @DisplayName("Удалить  книгу")
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(
                        post("/delete-book/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(302));

    }

    @Test
    @DisplayName("Сохранить коммент")
    public void shouldSaveComment() throws Exception {

        CommentDto comment = new CommentDto(-1, "user", LocalDateTime.now(), "comment", 1);

        mockMvc.perform(
                        post("/save-comment/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().is(302));

    }

    @Test
    @DisplayName("Получить страницу с редактированием  коммента")
    public void shouldReturnEditCommentPage() throws Exception {

        when(libraryService.getCommentById(EXISTING_ID)).thenReturn(comment);

        mockMvc.perform(get("/edit-comment/" + EXISTING_ID))
                .andExpect(view().name("comment-edit"))
                .andExpect(model().attribute("comment", notNullValue()))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Получить страницу с комментами")
    public void shouldReturnCommentsPage() throws Exception {
        when(libraryService.getCommentById(EXISTING_ID)).thenReturn(comment);
        when(libraryService.getBookById(EXISTING_ID)).thenReturn(book);

        mockMvc.perform(get("/book-comments/" + EXISTING_ID))
                .andExpect(model().attribute("book", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Удалить коммент")
    public void shouldDeleteComment() throws Exception {

        when(libraryService.getCommentById(EXISTING_ID)).thenReturn(comment);

        mockMvc.perform(
                        post("/delete-comment/" + EXISTING_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(302));

    }
}