package ru.otus.spring082022.homework06.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring082022.homework06.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a", Author.class);
        return query.getResultList();

    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

}
