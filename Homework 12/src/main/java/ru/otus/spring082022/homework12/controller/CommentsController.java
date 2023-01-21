package ru.otus.spring082022.homework12.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring082022.homework12.domain.Comment;
import ru.otus.spring082022.homework12.dto.CommentDto;
import ru.otus.spring082022.homework12.service.LibraryService;
import ru.otus.spring082022.homework12.service.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentsController {
    private final LibraryService libraryService;

    @GetMapping("/api/comments/bybook/{bookId}")
    public ResponseEntity<List<CommentDto>> getCommentsByBook(@PathVariable long bookId) throws ServiceException {
        try {
            List<Comment> comments = libraryService.listCommentsByBook(bookId);
            if (comments == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comments.stream().map(CommentDto::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long commentId) throws ServiceException {
        try {
            Comment comment = libraryService.getCommentById(commentId);
            if (comment == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<CommentDto>(CommentDto.toDto(comment), HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @PostMapping("/api/comments/")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto comment) throws ServiceException {
        try {
            libraryService.saveComment(CommentDto.toDomain(comment));
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long commentId) throws ServiceException {
        try {
            libraryService.deleteCommentById(commentId);
            return ResponseEntity.ok(commentId);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}


