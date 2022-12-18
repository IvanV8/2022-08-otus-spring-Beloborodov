package ru.otus.spring082022.homework10b.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring082022.homework10b.domain.Comment;
import ru.otus.spring082022.homework10b.dto.CommentDto;
import ru.otus.spring082022.homework10b.service.LibraryService;
import ru.otus.spring082022.homework10b.service.ObjectNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentsController {
    private final LibraryService libraryService;

    @GetMapping("/api/comments/bybook/{bookId}")
    public ResponseEntity<List<CommentDto>> getCommentsByBook(@PathVariable long bookId) {
        try {
            List<Comment> comments = libraryService.listCommentsByBook(bookId);
            if (comments == null) throw new ObjectNotFoundException("No comments found");
            return new ResponseEntity<>(comments.stream().map(CommentDto::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long commentId) {
        try {
            Comment comment = libraryService.getCommentById(commentId);
            if (comment == null) throw new ObjectNotFoundException("No comment with id");
            return new ResponseEntity<CommentDto>(CommentDto.toDto(comment), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/comments/")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto comment) {
        try {
            libraryService.saveComment(CommentDto.toDomain(comment));
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long commentId) {
        long bookId = libraryService.getCommentById(commentId).getBook().getId();
        libraryService.deleteCommentById(commentId);
        return ResponseEntity.ok(bookId);
    }
}


