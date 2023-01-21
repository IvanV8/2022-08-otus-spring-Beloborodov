package ru.otus.spring082022.homework08.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.spring082022.homework08.domain.Book;
import ru.otus.spring082022.homework08.domain.Comment;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void saveBookEverywhere(Book book) {

        Query select = Query.query(Criteria.where("book._id").is(book.getId()));
        Update update = new Update();
        update.set("book", book);
        mongoTemplate.updateMulti(select, update, Comment.class);

    }
}
