package ru.otus.spring082022.homework13.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring082022.homework13.domain.Book;
import ru.otus.spring082022.homework13.domain.Comment;
import ru.otus.spring082022.homework13.domain.LibraryUser;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id = -1;
    private UserDto userName;
    private LocalDateTime commentDateTime;
    private String text;
    private long bookId;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(),
                UserDto.toDto(comment.getUserName()),
                comment.getCommentDateTime(),
                comment.getText(),
                comment.getBook().getId());
    }

    public static Comment toDomain(CommentDto commentDto) {
        return new Comment(commentDto.getId(),
                (commentDto.getUserName() == null) ? new LibraryUser() : UserDto.toDomain(commentDto.getUserName()),
                commentDto.getCommentDateTime(),
                commentDto.getText(), new Book(commentDto.bookId));
    }
}
