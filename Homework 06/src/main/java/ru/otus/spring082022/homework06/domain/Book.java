package ru.otus.spring082022.homework06.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false, unique = false)
    private String title;
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;
    @OneToMany(targetEntity = Comment.class, mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments;


    public Book(long i, String title, String isbn, Author author, Genre genre) {
        this.id = i;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

}
