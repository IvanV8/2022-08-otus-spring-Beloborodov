package ru.otus.spring082022.homework09.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework09.domain.Author;
import ru.otus.spring082022.homework09.domain.Book;
import ru.otus.spring082022.homework09.domain.Comment;
import ru.otus.spring082022.homework09.domain.Genre;
import ru.otus.spring082022.homework09.repositories.AuthorRepository;
import ru.otus.spring082022.homework09.repositories.BookRepository;
import ru.otus.spring082022.homework09.repositories.CommentRepository;
import ru.otus.spring082022.homework09.repositories.GenreRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id).orElse(null);
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
        try {
            if (bookRepository.findById(id).isEmpty()) {
                throw new ObjectNotFoundException(String.format("No book with id:%d", id));
            }
            commentRepository.deleteAllByBookId(id);
            bookRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            return;
        }
    }

    @Override
    @Transactional
    public void deleteCommentById(long id) {
        try {
            if (commentRepository.findById(id).isEmpty()) {
                throw new ObjectNotFoundException(String.format("No comment with id:%d", id));
            }
            commentRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            return;
        }
    }

    @Override
    public List<Comment> listCommentsByBook(long bookId) {
        try {
            List<Comment> comments = commentRepository.findAllByBookId(bookId);
            if (comments == null) {
                throw new ObjectNotFoundException(String.format("No comment with book id:%d", bookId));
            }
            return comments;
        } catch (ObjectNotFoundException e) {
            return new ArrayList<Comment>();
        }

    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }


    @Override
    @Transactional
    public void saveComment(Comment comment, long bookId) {
        try {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No book with id:%d", bookId)));
            comment.setCommentDateTime(LocalDateTime.now());
            comment.setBook(book);
            commentRepository.save(comment);
        } catch (ObjectNotFoundException e) {
            return;
        }

    }

}
