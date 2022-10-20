package ru.otus.spring082022.homework06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework06.domain.Book;
import ru.otus.spring082022.homework06.service.ObjectNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        return Optional.ofNullable(em.find(Book.class, id));
    }


    @Override
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
    public void deleteById(long id) {
        try {
            Book book = Optional.ofNullable(em.find(Book.class, id)).orElseThrow(() -> new ObjectNotFoundException());
            em.remove(em.find(Book.class, id));
        } catch (Exception e) {
            return;
        }
    }
}



