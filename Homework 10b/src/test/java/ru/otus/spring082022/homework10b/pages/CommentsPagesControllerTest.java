package ru.otus.spring082022.homework10b.pages;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework10b.Homework10bApplication;
import ru.otus.spring082022.homework10b.service.LibraryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ContextConfiguration(classes = Homework10bApplication.class)
@WebMvcTest(controllers = CommentsPagesController.class)

public class CommentsPagesControllerTest {

    private static final long EXISTING_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    @DisplayName("Получить страницу с редактированием  коммента")
    public void shouldReturnEditCommentPage() throws Exception {
        mockMvc.perform(get("/edit-comment/" + EXISTING_ID))
                .andExpect(view().name("comment-edit"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить страницу с комментами")
    public void shouldReturnCommentsPage() throws Exception {

        mockMvc.perform(get("/book-comments/" + EXISTING_ID))
                .andExpect(status().isOk());
    }

}