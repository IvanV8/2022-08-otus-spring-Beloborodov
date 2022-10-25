package ru.otus.spring082022.homework08.сommandlinerunners;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.service.BookService;
import ru.otus.spring082022.homework08.service.InOutService;

import java.util.List;


// реализация команд оболочки spring shell
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {
    private final BookService bookService;

    private final InOutService ioService;

    @ShellMethod(value = "List", key = {"l", "list"}, group = "Books")
    public void listBooks() {
        List<Book> books = bookService.listAllBooks();
        ioService.outStringn("         ID                            TITLE                ISBN                       AUTHOR                   GENRE");
        ioService.outStringn("---------------------------------------------------------------------------------------------------------------------");

        for (Book b : books) {
            ioService.outStringn(String.format("%9d %35s %20s %28s %20s", b.getId(), b.getTitle(), b.getIsbn(), b.getAuthor().getName(), b.getGenre().getName()));
        }
    }


    @ShellMethod(value = "Authors", key = {"a", "authors"}, group = "Books")
    public void listAuthors() {
        List<Author> authors = bookService.listAllAuthors();
        ioService.outStringn("         ID                               NAME");
        ioService.outStringn("----------------------------------------------");

        for (Author a : authors) {
            ioService.outStringn(String.format("%9d %35s", a.getId(), a.getName()));
        }

    }

    @ShellMethod(value = "Genres", key = {"g", "genres"}, group = "Books")
    public void listGenres() {

        List<Genre> genres = bookService.listAllGenres();
        ;
        ioService.outStringn("         ID                               NAME");
        ioService.outStringn("----------------------------------------------");

        for (Genre g : genres) {
            ioService.outStringn(String.format("%9d %35s", g.getId(), g.getName()));
        }
    }

    @ShellMethod(value = "New", key = {"n", "new"}, group = "Books")
    public void newBook() {

        ioService.outStringn(String.format("New book added with id:%d", bookService.newBook()));
    }

    @ShellMethod(value = "Comment", key = {"c", "comment"}, group = "Books")
    public void newComment() {

        ioService.outStringn(String.format("New comment added with id:%d", bookService.newComment()));
    }

    @ShellMethod(value = "Edit", key = {"e", "edit"}, group = "Books")
    public String editBook(@ShellOption(help = "Enter id", defaultValue = "0") long id) {
        if (id == 0)
            id = ioService.inLongWithPrompt("Enter ID:");
        bookService.updateBook(id);
        return String.format("Book updated with id:%d", id);
    }

    @ShellMethod(value = "Book comments", key = {"bc", "book comments"}, group = "Books")

    public void bookComments(@ShellOption(help = "Enter id", defaultValue = "0") long id) {
        if (id == 0)
            id = ioService.inLongWithPrompt("Enter book ID:");
        List<Comment> comments = bookService.listCommentsByBook(id);
        if (comments == null || comments.size() == 0)
            ioService.outStringn("No comments for book.");
        else {
            ioService.outStringn("Comments:");
            for (Comment c : comments) {
                ioService.outStringn(String.format("id:%d, author:%s, date:%tD, comment:%s",
                        c.getId(), c.getUserName(), c.getCommentDateTime(), c.getText()));
            }
        }
    }


    @ShellMethod(value = "Delete", key = {"d", "delete"}, group = "Books")
    public String deleteBook(@ShellOption(help = "Enter id", defaultValue = "0") long id) {
        if (id == 0)
            id = ioService.inLongWithPrompt("Enter ID:");

        bookService.deleteBookById(id);
        return String.format("Book deleted with id:%d", id);
    }

    @ShellMethod(value = "Report", key = {"r", "report"}, group = "Books")
    public String countBook() {
        return String.format("Total number of books:%d", bookService.reportCount());
    }


}


