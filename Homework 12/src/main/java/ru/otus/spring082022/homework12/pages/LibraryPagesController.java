package ru.otus.spring082022.homework12.pages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring082022.homework12.domain.Book;
import ru.otus.spring082022.homework12.dto.BookDto;
import ru.otus.spring082022.homework12.service.LibraryService;

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


}
