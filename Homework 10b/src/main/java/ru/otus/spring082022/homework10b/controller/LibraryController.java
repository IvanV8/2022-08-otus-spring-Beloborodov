package ru.otus.spring082022.homework10b.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring082022.homework10b.dto.BookDto;
import ru.otus.spring082022.homework10b.dto.CommentDto;
import ru.otus.spring082022.homework10b.service.LibraryService;

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

    @DeleteMapping(value = "/api/delete-book/{bookId}")
    public ResponseEntity<Long> deleteBook(@PathVariable long bookId) {
        libraryService.deleteBookById(bookId);
        return ResponseEntity.ok(bookId);
    }

    @DeleteMapping("/api/delete-comment/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long commentId) {
        long bookId = libraryService.getCommentById(commentId).getBook().getId();
        libraryService.deleteCommentById(commentId);
        return ResponseEntity.ok(bookId);
    }
}


