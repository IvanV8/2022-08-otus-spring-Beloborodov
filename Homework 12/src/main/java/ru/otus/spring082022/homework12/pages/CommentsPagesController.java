package ru.otus.spring082022.homework12.pages;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring082022.homework12.service.LibraryService;

@Controller
@AllArgsConstructor
public class CommentsPagesController {

    private final LibraryService libraryService;


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
