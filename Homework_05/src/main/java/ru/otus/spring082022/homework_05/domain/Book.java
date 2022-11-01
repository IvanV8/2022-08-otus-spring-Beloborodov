package ru.otus.spring082022.homework_05.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private long id;
    private String title;
    private String isbn;
    private Author author;
    private Genre genre;


}
