package ru.otus.spring082022.homework08.commandlinerunners;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring082022.homework08.service.BookService;


// реализация команд оболочки spring shell
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {
    private final BookService bookService;

    @ShellMethod(value = "List", key = {"l", "list"}, group = "Books")
    public void listBooks() {
        bookService.listAllBooks();
    }
    @ShellMethod(value = "Authors", key = {"a", "authors"}, group = "Books")
    public void listAuthors() {
        bookService.listAllAuthors();
    }

    @ShellMethod(value = "Genres", key = {"g", "genres"}, group = "Books")
    public void listGenres() {
        bookService.listAllGenres();
    }

    @ShellMethod(value = "New", key = {"n", "new"}, group = "Books")
    public void newBook() {
        bookService.newBook();
    }

    @ShellMethod(value = "Comment", key = {"c", "comment"}, group = "Books")
    public void newComment() {
        bookService.newComment();
    }

    @ShellMethod(value = "Edit", key = {"e", "edit"}, group = "Books")
    public void editBook(@ShellOption(help = "Enter id", defaultValue = "") String id) {
        bookService.updateBook(id);
    }

    @ShellMethod(value = "Book comments", key = {"bc", "book comments"}, group = "Books")
    public void bookComments(@ShellOption(help = "Enter id", defaultValue = "") String id) {
        bookService.listCommentsByBook(id);
    }

    @ShellMethod(value = "Delete", key = {"d", "delete"}, group = "Books")
    public void deleteBook(@ShellOption(help = "Enter id", defaultValue = "") String id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Report", key = {"r", "report"}, group = "Books")
    public String countBook() {
        return bookService.reportCount();
    }
}


