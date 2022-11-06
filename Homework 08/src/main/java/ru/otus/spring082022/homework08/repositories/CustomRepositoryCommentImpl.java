package ru.otus.spring082022.homework08.repositories;


import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring082022.homework08.domain.Comment;

import java.util.List;


@RequiredArgsConstructor
public class CustomRepositoryCommentImpl implements CustomRepositoryComment {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAllByBookId(String bookId) {

        return null;
    }


}
