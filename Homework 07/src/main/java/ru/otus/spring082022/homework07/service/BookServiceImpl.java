package ru.otus.spring082022.homework07.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework07.domain.Author;
import ru.otus.spring082022.homework07.domain.Book;
import ru.otus.spring082022.homework07.domain.Comment;
import ru.otus.spring082022.homework07.domain.Genre;
import ru.otus.spring082022.homework07.repositories.AuthorRepository;
import ru.otus.spring082022.homework07.repositories.BookRepository;
import ru.otus.spring082022.homework07.repositories.CommentRepository;
import ru.otus.spring082022.homework07.repositories.GenreRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;
    private final InOutService ioService;

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Comment> listAllCommentsByBook(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public Comment updateComment(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            ioService.outStringn("Сomment not found with id");
            return null;
        }
        ioService.outStringn("Сurrent comment:");
        ioService.outStringn(String.format("Author:%20s, Date:%tD: Comment:%50s",
                comment.get().getUserName(), comment.get().getCommentDateTime(), comment.get().getText()));
        comment.get().setUserName(ioService.inStringWithPrompt("Author:"));
        comment.get().setCommentDateTime(LocalDateTime.now());
        comment.get().setText(ioService.inStringWithPrompt("Comment:"));
        commentRepository.save(comment.get());
        return comment.get();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }


    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();

    }

    @Override
    public List<Genre> listAllGenres() {
        return genreRepository.findAll();

    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        commentRepository.deleteAllByBook(id);
        bookRepository.deleteById(id);
    }


    @Override
    public Long reportCount() {
        return bookRepository.count();
    }

    @Override
    public long newComment() {
        try {
            long bookId = ioService.inLongWithPrompt("Enter book ID:");
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Книга не найдена с id:%d", bookId)));
            String userName = ioService.inStringWithPrompt("Enter your name:");
            String textComment = ioService.inStringWithPrompt("Enter your comment:");
            Comment comment = new Comment(0, userName, LocalDateTime.now(), textComment, book);
            return commentRepository.save(comment).getId();
        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Не найден объект в БД с заданным id");
            return 0;
        }
    }


    @Override
    public List<Comment> listCommentsByBook(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public long newBook() {
        try {
            String title = ioService.inStringWithPrompt("Enter TITLE:");
            String isbn = ioService.inStringWithPrompt("Enter ISBN:");
            long author_id = ioService.inLongWithPrompt("Enter author id:");

            long genre_id = ioService.inLongWithPrompt("Enter genre id:");
            Author author = authorRepository.findById(author_id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Автор не найден с id:%d", author_id)));

            Genre genre = genreRepository.findById(genre_id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Жанр не найден с id:%d", author_id)));
            Book book = new Book(0, title, isbn, author, genre);
            return bookRepository.save(book).getId();
        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Не найден объект в БД с заданным id");
            return 0;
        }
    }

    @Override
    public void updateBook(long id) {
        try {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Книга не найдена с id:%d", id)));
            // если пользователь ввел пустую строку, то не меняем поле объекта
            String title = ioService.inStringWithPrompt(String.format("Enter new TITLE (Enter to leave:%s):", book.getTitle()));
            if (!title.equals("")) {
                book.setTitle(title);
            }
            // если пользователь ввел пустую строку, то не меняем поле объекта
            String isbn = ioService.inStringWithPrompt(String.format("Enter new ISBN (Enter to leave:%s):", book.getIsbn()));
            if (!isbn.equals("")) {
                book.setIsbn(isbn);
            }
            // если пользователь ввел 0, то не меняем поле объекта
            long author_id = ioService.inLongWithPrompt(String.format("Enter new AUTHOR ID (Enter 0 to leave:%d):", book.getAuthor().getId()));
            if (author_id == 0) {
                author_id = book.getAuthor().getId();
            }
            // если пользователь ввел 0, то не меняем поле объекта
            long genre_id = ioService.inLongWithPrompt(String.format("Enter new GENRE ID (Enter 0 to leave:%d):", book.getGenre().getId()));
            if (genre_id == 0) {
                genre_id = book.getGenre().getId();
            }
            Author author = authorRepository.findById(author_id)
                    .orElseThrow(() -> new ObjectNotFoundException("Автор не найден"));

            Genre genre = genreRepository.findById(genre_id)
                    .orElseThrow(() -> new ObjectNotFoundException("Жанр не найден"));
            book.setAuthor(author);
            book.setGenre(genre);

            bookRepository.save(book);

        } catch (ObjectNotFoundException e) {
            ioService.outStringn("Не найден объект в БД с заданным id");
        }

    }


}
