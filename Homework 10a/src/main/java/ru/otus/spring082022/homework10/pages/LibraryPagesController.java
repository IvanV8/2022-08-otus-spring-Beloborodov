package ru.otus.spring082022.homework10.pages;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.spring082022.homework10.domain.Author;
import ru.otus.spring082022.homework10.domain.Book;
import ru.otus.spring082022.homework10.domain.Genre;
import ru.otus.spring082022.homework10.dto.BookDto;
import ru.otus.spring082022.homework10.dto.CommentDto;
import ru.otus.spring082022.homework10.service.LibraryService;

import java.util.List;

@Controller
@AllArgsConstructor
public class LibraryPagesController {

    private final LibraryService libraryService;

    @GetMapping("/")
    public String listPage(Model model) {
        return "library";
    }

    @GetMapping("/new-book")
    public String newBook(Model model) {
        List<Author> authors = libraryService.listAllAuthors();
        List<Genre> genres = libraryService.listAllGenres();
        model.addAttribute("book", new BookDto());
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book-edit";
    }

    @GetMapping("/edit-book/{bookId}")
    public String editBook(@PathVariable long bookId, Model model) {
        List<Author> authors = libraryService.listAllAuthors();
        List<Genre> genres = libraryService.listAllGenres();
        model.addAttribute("book", BookDto.toDto(libraryService.getBookById(bookId)));
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book-edit";
    }

    @GetMapping("/edit-comment/{commentId}")
    public String editComment(@PathVariable long commentId, Model model) {
        model.addAttribute("comment", CommentDto.toDto(libraryService.getCommentById(commentId)));
        return "comment-edit";
    }

    @GetMapping("/new-comment/{bookId}")
    public String newComment(@PathVariable long bookId, Model model) {
        CommentDto commentDto = new CommentDto();
        commentDto.setBookId(bookId);
        model.addAttribute("comment", commentDto);
        return "comment-edit";
    }

    @GetMapping("/book-comments/{bookId}")
    public String commentsByBook(@PathVariable long bookId, Model model) {
        Book book = libraryService.getBookById(bookId);
        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "comments not found"
            );
        }
        model.addAttribute("book", BookDto.toDto(book));
        return "comments";

    }

    @GetMapping("/delete-book/{bookId}")
    public String deleteBook(@PathVariable long bookId, Model model) {
        libraryService.deleteBookById(bookId);
        return "library";
    }

    @GetMapping("/delete-comment/{commentId}")
    public String deleteComment(@PathVariable long commentId, Model model) {
        long bookId = libraryService.getCommentById(commentId).getBook().getId();
        libraryService.deleteCommentById(commentId);
        model.addAttribute("book", BookDto.toDto(libraryService.getBookById(bookId)));
        return "comments";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute BookDto book) {
        libraryService.saveBook(BookDto.toDomain(book));
        return "library";
    }

    @PostMapping("/save-comment/{bookId}")
    public String saveComment(@PathVariable long bookId, @ModelAttribute CommentDto comment) {
        libraryService.saveComment(CommentDto.toDomain(comment), bookId);
        return "library";
    }


}
