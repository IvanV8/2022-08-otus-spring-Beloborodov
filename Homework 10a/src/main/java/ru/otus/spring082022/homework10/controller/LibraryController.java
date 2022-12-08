package ru.otus.spring082022.homework10.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring082022.homework10.dto.BookDto;
import ru.otus.spring082022.homework10.dto.CommentDto;
import ru.otus.spring082022.homework10.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return libraryService.listAllBooks().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/comments")
    public List<CommentDto> getCommentsByBook(@RequestParam("bookid") long bookId) {
        return libraryService.listCommentsByBook(bookId).stream().map(CommentDto::toDto)
                .collect(Collectors.toList());
    }
}


