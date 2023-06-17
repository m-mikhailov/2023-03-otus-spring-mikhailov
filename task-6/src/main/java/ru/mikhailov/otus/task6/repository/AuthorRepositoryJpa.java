package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Author;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    @Override
    public Author save(Author author) {
        if (Objects.isNull(author.getId())) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Author with id %s not found".formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

}
