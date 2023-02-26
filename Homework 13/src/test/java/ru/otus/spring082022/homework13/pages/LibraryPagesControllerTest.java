package ru.otus.spring082022.homework13.pages;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring082022.homework13.service.LibraryService;
import ru.otus.spring082022.homework13.service.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(controllers = LibraryPagesController.class)
public class LibraryPagesControllerTest {

    private static final long EXISTING_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private UserServiceImpl userService;

    @Test
    @WithMockUser(username = "test_user")
    @DisplayName("Получить страницу с книгами")
    public void shouldReturnLibraryPage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить страницу с книгами 403")
    public void shouldReturnLibraryPage403() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test_user")
    @DisplayName("Получить страницу с редактированием новой книги")
    public void shouldReturnNewBookPage() throws Exception {

        mockMvc.perform(get("/new-book"))
                .andExpect(view().name("book-edit"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить страницу с редактированием новой книги 403")
    public void shouldReturnNewBookPage403() throws Exception {

        mockMvc.perform(get("/new-book"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "test_user")
    @DisplayName("Получить страницу с редактированием  книги")
    public void shouldReturnEditBookPage() throws Exception {

        mockMvc.perform(get("/edit-book/" + EXISTING_ID))
                .andExpect(view().name("book-edit"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Получить страницу с редактированием  книги 403")
    public void shouldReturnEditBookPage403() throws Exception {

        mockMvc.perform(get("/edit-book/" + EXISTING_ID))
                .andExpect(status().isForbidden());
    }
}