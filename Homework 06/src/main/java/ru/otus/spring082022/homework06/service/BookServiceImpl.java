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
    public List<Comment> listAllCommentsByBook(long bookId) {
        return commentRepository.getAllByBook(bookId);
    }

    @Override
    @Transactional
    public Comment updateComment(long commentId, String userName, String textComment) {
        try {
            Comment comment = commentRepository.getById(commentId)
                    .orElseThrow(() -> new ObjectNotFoundException("Сomment not found with id"));
            comment.setUserName(userName);
            comment.setCommentDateTime(LocalDateTime.now());
            comment.setText(textComment);
            commentRepository.save(comment);
            return comment;
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.getById(id).orElse(null);
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
    @Transactional
    public long newComment(long bookId, String userName, String textComment) {
        try {
            Book book = bookRepository.getById(bookId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Книга не найдена с id:%d", bookId)));
            ioService.inStringWithPrompt("");
            Comment comment = new Comment(0, userName, LocalDateTime.now(), textComment, book);
            return commentRepository.save(comment).getId();
        } catch (ObjectNotFoundException e) {
            return 0;
        }
    }


    @Override
    public List<Comment> listCommentsByBook(long bookId) {
        return commentRepository.getAllByBook(bookId);
    }

    @Override
    @Transactional
    public long newBook(String title, String isbn, long authorId, long genreId) {
        try {
            Author author = authorRepository.getById(authorId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Автор не найден с id:%d", authorId)));

            Genre genre = genreRepository.getById(genreId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Жанр не найден с id:%d", genreId)));
            Book book = new Book(0, title, isbn, author, genre);
            return bookRepository.save(book).getId();
        } catch (ObjectNotFoundException e) {
            return 0;
        }
    }


    @Override
    @Transactional
    public Book updateBook(long id, String title, String isbn, long authorId, long genreId) {
        try {
            Book book = bookRepository.getById(id)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("Книга не найдена с id:%d", id)));
            book.setTitle(title);
            book.setIsbn(isbn);
            Author author = authorRepository.getById(authorId)
                    .orElseThrow(() -> new ObjectNotFoundException("Автор не найден"));

            Genre genre = genreRepository.getById(genreId)
                    .orElseThrow(() -> new ObjectNotFoundException("Жанр не найден"));
            book.setAuthor(author);
            book.setGenre(genre);

            bookRepository.save(book);
            return book;
        } catch (ObjectNotFoundException e) {
            return null;
        }
    }


}
