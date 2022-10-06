package ru.otus.spring082022.homework_05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.homework_05.dao.AuthorDAO;
import ru.otus.spring082022.homework_05.dao.BookDAO;
import ru.otus.spring082022.homework_05.dao.GenreDAO;
import ru.otus.spring082022.homework_05.domain.Author;
import ru.otus.spring082022.homework_05.domain.Book;
import ru.otus.spring082022.homework_05.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;
    private final InOutService ioService;


    @Override
    public List<Book> listAllBooks() {
        return bookDAO.getAll();

    }

    @Override
    public List<Author> listAllAuthors() {
        return authorDAO.getAll();

    }

    @Override
    public List<Genre> listAllGenres() {
        return genreDAO.getAll();

    }

    @Override
    public void delete(long id) {
        try {

            Book book = bookDAO.getById(id);
            if (book == null) {
                throw new ObjectNotFoundException(String.format("No book found with id:%d", id));
            }
            bookDAO.deleteById(id);

        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
        }
    }

    @Override
    public int reportCount() {
        return bookDAO.count();
    }

    @Override
    public void newBook() {
        try {
            String title = ioService.inStringWithPrompt("Enter TITLE:");
            String isbn = ioService.inStringWithPrompt("Enter ISBN:");
            long author_id = ioService.inLongWithPrompt("Enter author id:");
            Author author = authorDAO.getById(author_id);
            if (author == null) {
                throw new ObjectNotFoundException(String.format("No author found with id:%d", author_id));
            }
            long genre_id = ioService.inLongWithPrompt("Enter genre id:");
            Genre genre = genreDAO.getById(genre_id);
            if (genre == null) {
                throw new ObjectNotFoundException(String.format("No genre found with id:%d", genre_id));
            }
            Book book = new Book(0, title, isbn, author, genre);
            ioService.outStringn(String.format("New book added with id:%d", bookDAO.insert(book)));
        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
        }
    }

    @Override
    public void updateBook(long id) {
        try {
            Book book = bookDAO.getById(id);
            if (book == null) {
                throw new ObjectNotFoundException(String.format("No book found with id:%d", id));
            }
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
                author_id = book.getGenre().getId();
            }
            Author author = authorDAO.getById(author_id);
            if (author == null) {
                throw new ObjectNotFoundException(String.format("No author found with id:%d", author_id));
            }
            Genre genre = genreDAO.getById(genre_id);
            if (genre == null) {
                throw new ObjectNotFoundException(String.format("No genre found with id:%d", genre_id));
            }
            bookDAO.updateById(new Book(id, title, isbn, author, genre));
            ioService.outStringn(String.format("Book updated with id:%d", id));
        } catch (ObjectNotFoundException e) {
            ioService.outStringn(e.getMessage());
        }

    }


}
