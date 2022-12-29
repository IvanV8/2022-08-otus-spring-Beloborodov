package ru.otus.spring082022.homework08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring082022.homework08.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query(value = "{ 'book._id' : ?0}", delete = true)
    void deleteAllByBook(String bookId);


    @Query(value = "{ 'book._id' : ?0}")
    List<Comment> findAllByBookId(String bookId);
}
