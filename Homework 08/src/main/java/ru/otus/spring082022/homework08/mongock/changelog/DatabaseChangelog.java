package ru.otus.spring082022.homework08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.repositories.BookRepository;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "ibeloborodov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }


    @ChangeSet(order = "002", id = "insertPushkin", author = "ibeloborodov")
    public void insertPushkin(BookRepository repository) {
        repository.save(new Book("Pushkin"));
    }
}
