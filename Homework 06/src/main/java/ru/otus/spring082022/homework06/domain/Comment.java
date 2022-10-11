package ru.otus.spring082022.homework06.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name", nullable = false, unique = false)
    private String userName;
    @Column(name = "datetime", nullable = false, unique = false)
    private LocalDateTime commentDateTime;
    @Column(name = "text", nullable = false, unique = false)
    private String text;
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
}
