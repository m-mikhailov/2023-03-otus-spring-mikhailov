package ru.mikhailov.otus.task6.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Book save(Book book) {
        if (Objects.isNull(book.getId())) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id))
                .orElseThrow(() -> new EntityNotFoundException("Book with id %s not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return em.createQuery("select b from Book b " +
                                "join fetch b.genre " +
                                "join fetch b.author",
                        Book.class)
                .getResultList();
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteById(Long id) {
        Query query = em.createQuery(
                "delete from Book b where b.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getBookCommentsById(Long bookId) {
        return em.createQuery(
                        "select c from Comment c " +
                                "join fetch c.book " +
                                "join fetch c.book.author " +
                                "join fetch c.book.genre " +
                                "where c.book.id = :book_id",
                        Comment.class
                )
                .setParameter("book_id", bookId)
                .getResultList();
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        if (Objects.isNull(comment.getId())) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }
}
