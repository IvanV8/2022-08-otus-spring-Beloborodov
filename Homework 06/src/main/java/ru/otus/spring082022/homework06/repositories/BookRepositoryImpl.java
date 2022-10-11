package ru.otus.spring082022.homework06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework06.domain.Book;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(b) from Book b", Long.class);
        return (Long) query.getSingleResult();
    }

    @Override
    public Optional<Book> getById(long id) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.id = :id"
                , Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();

    }

    @Override
    public List<Book> getAllWithComments() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre left join fetch b.comments", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.id = :id"
                , Book.class);
        query.setParameter("id", id);
        em.remove(query.getSingleResult());

    }
}



