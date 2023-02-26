package ru.otus.spring082022.homework13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework13.domain.*;
import ru.otus.spring082022.homework13.repositories.*;

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
    private final UserRepository userRepository;

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
    public Long saveBook(Book book) {
        return bookRepository.save(book).getId();
    }


    @Override
    @Transactional
    public Long saveComment(Comment comment, LibraryUser userDetails) {
        try {
            Book book = bookRepository.findById(comment.getBook().getId())
                    .orElseThrow(() -> new ObjectNotFoundException(String.format("No book with id")));
            LibraryUser libraryUser = userRepository.findUserByLoginIs(userDetails.getLogin());
            comment.setCommentDateTime(LocalDateTime.now());
            comment.setUserName(libraryUser);
            comment.setBook(book);
            return commentRepository.save(comment).getId();
        } catch (ObjectNotFoundException e) {
            return 0L;
        }

    }

}
