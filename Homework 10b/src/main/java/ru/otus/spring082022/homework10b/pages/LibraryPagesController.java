package ru.otus.spring082022.homework10b.pages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring082022.homework10b.domain.Book;
import ru.otus.spring082022.homework10b.dto.BookDto;
import ru.otus.spring082022.homework10b.service.LibraryService;

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
        model.addAttribute("bookId", -1);
        return "book-edit";
    }

    @GetMapping("/edit-book/{bookId}")
    public String editBook(@PathVariable long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "book-edit";
    }

    @GetMapping("/edit-comment/{commentId}")
    public String editComment(@PathVariable long commentId, Model model) {
        model.addAttribute("commentId", commentId);
        model.addAttribute("bookId", 0);
        return "comment-edit";
    }

    @GetMapping("/new-comment/{bookId}")
    public String newComment(@PathVariable long bookId, Model model) {
        model.addAttribute("commentId", -1);
        model.addAttribute("bookId", bookId);
        return "comment-edit";
    }

    @GetMapping("/book-comments/{bookId}")
    public String commentsByBook(@PathVariable long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "comments";
    }



}
