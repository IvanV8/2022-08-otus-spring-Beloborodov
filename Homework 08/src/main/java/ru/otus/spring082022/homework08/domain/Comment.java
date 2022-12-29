package ru.otus.spring082022.homework08.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String userName;
    private LocalDateTime commentDateTime;
    private String text;
    private Book book;

    public Comment(String userName, LocalDateTime dateTime, String text, Book book) {
        this.userName = userName;
        this.commentDateTime = dateTime;
        this.text = text;
        this.book = book;

    }
}
