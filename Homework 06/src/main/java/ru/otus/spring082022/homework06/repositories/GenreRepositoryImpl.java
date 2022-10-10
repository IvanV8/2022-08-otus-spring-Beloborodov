package ru.otus.spring082022.homework06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    public GenreRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g", Genre.class);
        return query.getResultList();

    }

    @Override
    public Optional<Genre> getById(long id) {
        TypedQuery<Genre> query = em.createQuery(
                "select a from Author a where a.id = :id"
                , Genre.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
