package ru.otus.spring082022.homework08.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring082022.homework08.domain.Author;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;
import ru.otus.spring082022.homework08.domain.Genre;
import ru.otus.spring082022.homework08.repositories.AuthorRepository;
import ru.otus.spring082022.homework08.repositories.BookRepository;
import ru.otus.spring082022.homework08.repositories.CommentRepository;
import ru.otus.spring082022.homework08.repositories.GenreRepository;

import java.time.LocalDateTime;

@ChangeLog
public class DatabaseChangelog {

    private Genre poemGenre;
    private Genre romanGenre;
    private Genre fictionGenre;
    private Author authorPushkin;
    private Author authorLermontov;
    private Author authorLem;
    private Book bookOnegin;
    private Book bookHero;
    private Book bookSolaris;
    private Comment comment1;


    @ChangeSet(order = "001", id = "dropDb", author = "ibeloborodov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenres", author = "ibeloborodov", runAlways = true)
    public void initGenres(GenreRepository repository) {
        poemGenre = repository.save(new Genre("Poem"));
        romanGenre = repository.save(new Genre("Roman"));
        fictionGenre = repository.save(new Genre("Fiction"));
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "ibeloborodov", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        authorPushkin = repository.save(new Author("A.Pushkin"));
        authorLermontov = repository.save(new Author("M.Lermontov"));
        authorLem = repository.save(new Author("S.Lem"));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "ibeloborodov", runAlways = true)
    public void initBooks(BookRepository repository) {
        bookOnegin = repository.save(new Book("Evgeniy Onegin", "23324-234-234",
                authorPushkin, poemGenre));
        bookHero = repository.save(new Book("The Hero of Our Time", "233234-112-23454",
                authorLermontov, romanGenre));
        bookSolaris = repository.save(new Book("Solaris", "6675674-8723-29411",
                authorLem, fictionGenre));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "ibeloborodov", runAlways = true)
    public void initComments(CommentRepository repository) {
        comment1 = repository.save(new Comment("Alexey Yablokov",
                LocalDateTime.now(), "Great boook!", bookOnegin));
    }
}
