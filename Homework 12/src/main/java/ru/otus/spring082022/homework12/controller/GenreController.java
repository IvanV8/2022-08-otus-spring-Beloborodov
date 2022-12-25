package ru.otus.spring082022.homework12.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring082022.homework12.domain.Genre;
import ru.otus.spring082022.homework12.service.LibraryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final LibraryService libraryService;


    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return libraryService.listAllGenres();
    }


}


