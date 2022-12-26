package ru.otus.spring082022.homework12.pages;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework12.service.LibraryService;
import ru.otus.spring082022.homework12.service.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@ExtendWith(SpringExtension.class)
@WithMockUser(username = "test_user")
@WebMvcTest(controllers = CommentsPagesController.class)

public class CommentsPagesControllerTest {

    private static final long EXISTING_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserServiceImpl userService;

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