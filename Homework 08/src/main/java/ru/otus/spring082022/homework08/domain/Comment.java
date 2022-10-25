package ru.otus.spring082022.homework08.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "comments")
public class Comment {
    @Id
    private long id;
    private String userName;
    private LocalDateTime commentDateTime;
    private String text;

    private Book book;
}
