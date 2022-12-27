package ru.otus.spring082022.homework08.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String title;
    private String isbn;
    private Author author;
    private Genre genre;

    public Book(String title, String isbn, Author author,
                Genre genre) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    public Book(String bookId) {
        this.id = bookId;
    }
}
