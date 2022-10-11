package ru.otus.spring082022.homework06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework06.domain.Author;
import ru.otus.spring082022.homework06.domain.Book;
import ru.otus.spring082022.homework06.domain.Comment;
import ru.otus.spring082022.homework06.domain.Genre;
import ru.otus.spring082022.homework06.repositories.AuthorRepository;
import ru.otus.spring082022.homework06.repositories.BookRepository;
import ru.otus.spring082022.homework06.repositories.CommentRepository;
import ru.otus.spring082022.homework06.repositories.GenreRepository;

import javax.transaction.Transactional;
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


    @Override
    public List<Book> listAllBooks() {
        return bookRepository.getAll();

    }

    @Override
    public List<Book> listAllBooksWithComments() {
        return bookRepository.getAllWithComments();

    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.getAll();

    }

    @Override
    public List<Genre> listAllGenres() {
        return genreRepository.getAll();

    }


    @Override
    public void delete(long id) {
        try {

            bookRepository.getById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No book found with id:%d", id)));
            bookRepository.deleteById(id);

        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
        }
    }

    @Override
    public Long reportCount() {
        return bookRepository.count();
    }

    @Override
    public long newComment() {
        try {
            long bookId = ioService.inLongWithPrompt("Enter book ID:");
            Book book = bookRepository.getById(bookId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No book found with id:%d", bookId)));
            String userName = ioService.inStringWithPrompt("Enter your name:");
            String textComment = ioService.inStringWithPrompt("Enter your comment:");
            Comment comment = new Comment(0, userName, LocalDateTime.now(), textComment, book);
            return commentRepository.save(comment).getId();
        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Comment> listCommentsByBook(long bookId) {
        return commentRepository.getAllByBook(bookId);
    }

    @Override
    @Transactional
    public long newBook() {
        try {
            String title = ioService.inStringWithPrompt("Enter TITLE:");
            String isbn = ioService.inStringWithPrompt("Enter ISBN:");
            long author_id = ioService.inLongWithPrompt("Enter author id:");

            long genre_id = ioService.inLongWithPrompt("Enter genre id:");
            Author author = authorRepository.getById(author_id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No author found with id:%d", author_id)));

            Genre genre = genreRepository.getById(genre_id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No genre found with id:%d", genre_id)));
            Book book = new Book(0, title, isbn, author, genre);
            return bookRepository.save(book).getId();
        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
            return 0;
        }
    }

    @Override
    public void updateBook(long id) {
        try {
            Book book = bookRepository.getById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No book found with id:%d", id)));
            // если пользователь ввел пустую строку, то не меняем поле объекта
            String title = ioService.inStringWithPrompt(String.format("Enter new TITLE (Enter to leave:%s):", book.getTitle()));
            if (title.equals("")) {
                title = book.getTitle();
            }
            // если пользователь ввел пустую строку, то не меняем поле объекта
            String isbn = ioService.inStringWithPrompt(String.format("Enter new ISBN (Enter to leave:%s):", book.getIsbn()));
            if (isbn.equals("")) {
                isbn = book.getIsbn();
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
            Author author = authorRepository.getById(author_id)
                    .orElseThrow(() -> new ObjectNotFoundException("No author found"));

            Genre genre = genreRepository.getById(genre_id)
                    .orElseThrow(() -> new ObjectNotFoundException("No genre found"));

            bookRepository.save(new Book(id, title, isbn, author, genre));

        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
        }

    }


}
