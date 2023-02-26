package ru.otus.spring082022.homework13.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring082022.homework13.domain.Author;
import ru.otus.spring082022.homework13.domain.Book;
import ru.otus.spring082022.homework13.domain.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id = -1;
    private String title;
    private String isbn;
    private Author author;
    private Genre genre;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getGenre());
    }

    public static Book toDomain(BookDto bookDto) {
        return new Book(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getIsbn(),
                bookDto.getAuthor(),
                bookDto.getGenre());
    }


}
