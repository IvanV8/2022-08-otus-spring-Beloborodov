package ru.otus.spring082022.homework06.сommandlinerunners;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring082022.homework06.domain.Author;
import ru.otus.spring082022.homework06.domain.Book;
import ru.otus.spring082022.homework06.domain.Comment;
import ru.otus.spring082022.homework06.domain.Genre;
import ru.otus.spring082022.homework06.service.BookService;
import ru.otus.spring082022.homework06.service.InOutService;

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
        ioService.inStringWithPrompt("");
        String title = ioService.inStringWithPrompt("Enter TITLE:");
        String isbn = ioService.inStringWithPrompt("Enter ISBN:");
        long authorId = ioService.inLongWithPrompt("Enter author id:");
        long genreId = ioService.inLongWithPrompt("Enter genre id:");
        ioService.outStringn(String.format("New book added with id:%d",
                bookService.newBook(title, isbn, authorId, genreId)));
    }

    @ShellMethod(value = "Comment", key = {"c", "comment"}, group = "Books")
    public void newComment() {
        long bookId = ioService.inLongWithPrompt("Enter book ID:");
        ioService.inStringWithPrompt("");
        String userName = ioService.inStringWithPrompt("Enter your name:");
        String textComment = ioService.inStringWithPrompt("Enter your comment:");
        ioService.outStringn(String.format("New comment added with id:%d",
                bookService.newComment(bookId, userName, textComment)));
    }

    @ShellMethod(value = "Edit Comment", key = {"ec", "edit comment"}, group = "Books")
    public void editComment() {
        long commentId = ioService.inLongWithPrompt("Enter comment ID:");
        ioService.inStringWithPrompt(""); // т.к. чтение числа
        String userName = ioService.inStringWithPrompt("Enter your name:");
        String textComment = ioService.inStringWithPrompt("Enter your comment:");
        ioService.outStringn(String.format("Comment updated with id:%d",
                bookService.updateComment(commentId, userName, textComment).getId()));
    }

    @ShellMethod(value = "Edit", key = {"e", "edit"}, group = "Books")
    public String editBook(@ShellOption(help = "Enter id", defaultValue = "0") long id) {
        if (id == 0)
            id = ioService.inLongWithPrompt("Enter ID:");
        Book book = bookService.getBookById(id);
        // если пользователь ввел пустую строку, то не меняем поле объекта
        ioService.inStringWithPrompt(""); // т.к. первая  строка пропускается
        String title = ioService.inStringWithPrompt(String.format("Enter new TITLE (Enter to leave:%s):", book.getTitle()));
        if (title.equals("")) {
            title = book.getTitle();
        } else book.setTitle(title);
        // если пользователь ввел пустую строку, то не меняем поле объекта
        String isbn = ioService.inStringWithPrompt(String.format("Enter new ISBN (Enter to leave:%s):", book.getIsbn()));
        if (isbn.equals("")) {
            isbn = book.getIsbn();
        } else book.setIsbn(isbn);
        // если пользователь ввел 0, то не меняем поле объекта
        long authorId = ioService.inLongWithPrompt(String.format("Enter new AUTHOR ID (Enter 0 to leave:%d):", book.getAuthor().getId()));
        if (authorId == 0) {
            authorId = book.getAuthor().getId();
        }
        // если пользователь ввел 0, то не меняем поле объекта
        long genreId = ioService.inLongWithPrompt(String.format("Enter new GENRE ID (Enter 0 to leave:%d):", book.getGenre().getId()));
        if (genreId == 0) {
            genreId = book.getGenre().getId();
        }

        bookService.updateBook(id, title, isbn, authorId, genreId);
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


