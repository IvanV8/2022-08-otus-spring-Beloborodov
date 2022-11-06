package ru.otus.spring082022.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PrintListsServiceImpl implements PrintListsService {


    private final InOutService ioService;

    @Override
    public void PrintBooks(List<Book> books) {
        ioService.outStringn("         ID                                    TITLE                ISBN                       AUTHOR                   GENRE");
        ioService.outStringn("-----------------------------------------------------------------------------------------------------------------------------");

        for (Book b : books) {
            ioService.outStringn(String.format("%9s %30s %20s %28s %20s", b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor().getName(), b.getGenre().getName()));
        }
    }

    @Override
    public void PrintComments(List<Comment> comments) {
        if (comments == null || comments.size() == 0) {
            ioService.outStringn("No comments for book.");
        } else {
            ioService.outStringn("Comments:");
            for (Comment c : comments) {
                ioService.outStringn(String.format("id:%s, author:%s, date:%tD, comment:%s",
                        c.getId(), c.getUserName(), c.getCommentDateTime(), c.getText()));
            }
        }
    }

    @Override
    public void PrintAuthors(List<Author> authors) {
        ioService.outStringn("         ID                                      NAME");
        ioService.outStringn("-----------------------------------------------------");

        for (Author a : authors) {
            ioService.outStringn(String.format("%9s %30s", a.getId(), a.getName()));
        }
    }

    @Override
    public void PrintGenres(List<Genre> genres) {
        ioService.outStringn("         ID                                      NAME");
        ioService.outStringn("-----------------------------------------------------");

        for (Genre g : genres) {
            ioService.outStringn(String.format("%9s %30s", g.getId(), g.getName()));
        }
    }
}
