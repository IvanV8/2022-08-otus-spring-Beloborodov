package ru.otus.spring082022.homework10.pages;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.spring082022.homework10.domain.Author;
import ru.otus.spring082022.homework10.domain.Book;
import ru.otus.spring082022.homework10.domain.Genre;
import ru.otus.spring082022.homework10.dto.BookDto;
import ru.otus.spring082022.homework10.dto.CommentDto;
import ru.otus.spring082022.homework10.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class LibraryPagesController {

    private final LibraryService libraryService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = libraryService.listAllBooks();
        model.addAttribute("books", books.stream().map(BookDto::toDto)
                .collect(Collectors.toList()));
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
        List<CommentDto> comments = libraryService.listCommentsByBook(bookId).stream().map(CommentDto::toDto)
                .collect(Collectors.toList());
        model.addAttribute("comments", comments);
        model.addAttribute("book", BookDto.toDto(book));
        return "comments";
    }

    @DeleteMapping(value = "/delete-book/{bookId}")
    public ResponseEntity<Long> deleteBook(@PathVariable long bookId) {
        libraryService.deleteBookById(bookId);
        return ResponseEntity.ok(bookId);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long commentId) {
        long bookId = libraryService.getCommentById(commentId).getBook().getId();
        libraryService.deleteCommentById(commentId);
        return ResponseEntity.ok(bookId);
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute BookDto book) {
        libraryService.saveBook(BookDto.toDomain(book));
        return "redirect:/";
    }

    @PostMapping("/save-comment/{bookId}")
    public String saveComment(@PathVariable long bookId, @ModelAttribute CommentDto comment) {
        libraryService.saveComment(CommentDto.toDomain(comment), bookId);
        return "redirect:/book-comments/" + bookId;
    }


}
