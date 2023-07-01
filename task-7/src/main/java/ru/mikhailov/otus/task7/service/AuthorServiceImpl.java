package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.AuthorDto;
import ru.mikhailov.otus.task7.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Author;
import ru.mikhailov.otus.task7.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    @Transactional
    public Author create(AuthorDto author) {
        var newAuthor = new Author(null, author.name());
        return repository.save(newAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %s not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return repository.findAll();
    }
}
