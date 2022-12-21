package ru.otus.spring082022.homework10b.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring082022.homework10b.domain.Author;
import ru.otus.spring082022.homework10b.domain.Book;
import ru.otus.spring082022.homework10b.domain.Genre;
import ru.otus.spring082022.homework10b.dto.BookDto;
import ru.otus.spring082022.homework10b.service.LibraryService;
import ru.otus.spring082022.homework10b.service.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final LibraryService libraryService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return libraryService.listAllBooks().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return libraryService.listAllAuthors();
    }

    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return libraryService.listAllGenres();
    }

    @GetMapping("/api/books/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId) {
        try {
            Book book = libraryService.getBookById(bookId);
            if (book == null) throw new ObjectNotFoundException("No book with id");
            return ResponseEntity.ok().body(BookDto.toDto(book));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/api/books")
    public ResponseEntity<?> saveBook(@RequestBody BookDto book) {
        try {
            libraryService.saveBook(BookDto.toDomain(book));
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/api/books/{bookId}")
    public ResponseEntity<Long> deleteBook(@PathVariable long bookId) {
        try {
            libraryService.deleteBookById(bookId);
            return new ResponseEntity<>(bookId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


