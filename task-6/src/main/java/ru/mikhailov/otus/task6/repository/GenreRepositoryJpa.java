package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Genre;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    @Override
    public Genre save(Genre genre) {
        if (Objects.isNull(genre.getId())) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %s not found".formatted(id)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }
}
