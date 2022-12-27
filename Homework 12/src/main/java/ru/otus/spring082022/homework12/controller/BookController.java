package ru.otus.spring082022.homework12.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring082022.homework12.domain.Book;
import ru.otus.spring082022.homework12.dto.BookDto;
import ru.otus.spring082022.homework12.service.LibraryService;
import ru.otus.spring082022.homework12.service.ServiceException;

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

    @GetMapping("/api/books/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId) throws ServiceException {
        try {
            Book book = libraryService.getBookById(bookId);
            if (book == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(BookDto.toDto(book));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @PostMapping("/api/books")
    public ResponseEntity<?> saveBook(@RequestBody BookDto book) throws ServiceException {
        try {
            libraryService.saveBook(BookDto.toDomain(book));
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @DeleteMapping(value = "/api/books/{bookId}")
    public ResponseEntity<Long> deleteBook(@PathVariable long bookId) throws ServiceException {
        try {
            libraryService.deleteBookById(bookId);
            return new ResponseEntity<>(bookId, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}


