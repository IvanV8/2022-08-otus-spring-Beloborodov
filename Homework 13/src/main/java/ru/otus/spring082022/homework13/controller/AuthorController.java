package ru.otus.spring082022.homework13.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring082022.homework13.domain.Author;
import ru.otus.spring082022.homework13.service.LibraryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final LibraryService libraryService;

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return libraryService.listAllAuthors();
    }


}


