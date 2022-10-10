package ru.otus.spring082022.homework06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework06.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public CommentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Comment> getById(long id) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.id = :id"
                , Comment.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public List<Comment> getAllByBook(long bookId) {

        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.book.id=:book_id", Comment.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }


}
