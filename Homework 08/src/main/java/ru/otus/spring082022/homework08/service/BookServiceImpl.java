package ru.otus.spring082022.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.AuthorRepository;
import ru.otus.spring082022.homework08.repositories.BookRepository;
import ru.otus.spring082022.homework08.repositories.CommentRepository;
import ru.otus.spring082022.homework08.repositories.GenreRepository;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;
    private final InOutService ioService;

    private final PrintListsService printService;

    @Override
    public void listAllBooks() {
        printService.printBooks(bookRepository.findAll());
    }

    @Override
    public void listCommentsByBook(String bookId) {
        if (bookId.equals("")) {
            bookId = ioService.inStringWithPrompt("Enter book ID:");
        }
        List<Comment> comments = commentRepository.findAllByBookId(bookId);
        if (comments == null || comments.size() == 0) {
            ioService.outStringn(String.format("No comments for book %s", bookId));
        } else {
            printService.printComments(commentRepository.findAllByBookId(bookId));
        }
    }

    @Override
    public Comment updateComment(String id) {
        try {
            Comment comment = commentRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("Comment not found"));
            ioService.outStringn("Сurrent comment:");
            ioService.outStringn(String.format("Author:%20s, Date:%tD: Comment:%50s",
                    comment.getUserName(), comment.getCommentDateTime(), comment.getText()));
            comment.setUserName(ioService.inStringWithPrompt("Author:"));
            comment.setCommentDateTime(LocalDateTime.now());
            comment.setText(ioService.inStringWithPrompt("Comment:"));
            commentRepository.save(comment);
            return comment;
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }


    @Override
    public void listAllAuthors() {
        printService.printAuthors(authorRepository.findAll());

    }

    @Override
    public void listAllGenres() {
        printService.printGenres(genreRepository.findAll());

    }

    @Override
    public void deleteBookById(String id) {
        try {
            if (id.equals("")) {
                id = ioService.inStringWithPrompt("Enter ID:");
            }
            if (!bookRepository.existsById(id)) {
                throw new ObjectNotFoundException("Book hasn't found with id");
            }
            commentRepository.deleteAllByBook(id);
            bookRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Object ");
        }
    }

    @Override
    public String deleteBook(String id) {
        if (id.equals("")) {
            id = ioService.inStringWithPrompt("Enter ID:");
        }
        deleteBookById(id);
        return String.format("Book deleted with id:%s", id);

    }


    @Override
    public String reportCount() {
        return String.format("Total number of books:%d", bookRepository.count());
    }

    @Override
    public void newComment() {
        try {
            String bookId = ioService.inStringWithPrompt("Enter book ID:");
            if (!bookRepository.existsById(bookId)) {
                throw new ObjectNotFoundException(String.format("Book not found with id:%s", bookId));
            }
            String userName = ioService.inStringWithPrompt("Enter your name:");
            String textComment = ioService.inStringWithPrompt("Enter your comment:");
            Comment comment = new Comment(userName, LocalDateTime.now(), textComment, new Book(bookId));
            ioService.outStringn(String.format("New comment added with id:%s", commentRepository.save(comment).getId()));
        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Object not found");
        }
    }


    @Override
    public void newBook() {
        try {
            String title = ioService.inStringWithPrompt("Enter TITLE:");
            String isbn = ioService.inStringWithPrompt("Enter ISBN:");
            String authorId = ioService.inStringWithPrompt("Enter author id:");
            String genreId = ioService.inStringWithPrompt("Enter genre id:");
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Author not found with id:%s", authorId)));
            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Genre not found with id:%s", genreId)));
            Book book = new Book(title, isbn, author, genre);
            bookRepository.save(book);
            ioService.outStringn(String.format("New book added with id:%s", bookRepository.save(book).getId()));
        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Object not found");
        }
    }

    @Override
    public String updateBook(String id) {
        try {
            if (id.equals("")) {
                id = ioService.inStringWithPrompt("Enter ID:");
            }

            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException("Книга не найдена c заданным id"));
            // если пользователь ввел пустую строку, то не меняем поле объекта
            String title = ioService.inStringWithPrompt(String.format("Enter new TITLE (Enter to leave:%s):", book.getTitle()));
            if (!title.equals("")) {
                book.setTitle(title);
            }
            String isbn = ioService.inStringWithPrompt(String.format("Enter new ISBN (Enter to leave:%s):", book.getIsbn()));
            if (!isbn.equals("")) {
                book.setIsbn(isbn);
            }
            String authorId = ioService.inStringWithPrompt(String.format("Enter new AUTHOR ID (Enter  to leave:%s):", book.getAuthor().getId()));
            if (authorId.equals("")) {
                authorId = book.getAuthor().getId();
            }
            String genreId = ioService.inStringWithPrompt(String.format("Enter new GENRE ID (Enter  to leave:%s):", book.getGenre().getId()));
            if (genreId.equals("")) {
                genreId = book.getGenre().getId();
            }
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new ObjectNotFoundException("Author not found"));

            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new ObjectNotFoundException("Genre not found"));
            book.setAuthor(author);
            book.setGenre(genre);

            bookRepository.saveBookEverywhere(book);
            return String.format("Book updated with id:%s", id);

        } catch (ObjectNotFoundException e) {
            return "Object not found";
        }

    }

}


