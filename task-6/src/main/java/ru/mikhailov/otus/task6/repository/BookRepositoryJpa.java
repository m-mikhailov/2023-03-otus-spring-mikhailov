package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task6.domain.model.Book;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book save(Book book) {
        if (Objects.isNull(book.getId())) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        return Optional.ofNullable(em.find(Book.class, id,
                Map.of("jakarta.persistence.fetchgraph", entityGraph)));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        var query = em.createQuery("select b from Book b", Book.class);
        query.setHint("jakarta.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void update(Book book) {
        Query query = em.createQuery(
                "update Book b " +
                        "set b.name = :name, " +
                        "b.author.id = :author_id, " +
                        "b.genre.id = :genre_id " +
                        "where b.id = :id"
        );
        query.setParameter("id", book.getId());
        query.setParameter("name", book.getName());
        query.setParameter("author_id", book.getAuthor().getId());
        query.setParameter("genre_id", book.getGenre().getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery(
                "delete from Book b where b.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
