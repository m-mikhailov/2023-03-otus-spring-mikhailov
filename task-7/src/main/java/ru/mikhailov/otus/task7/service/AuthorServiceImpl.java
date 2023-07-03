package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task7.domain.dto.AuthorDto;
import ru.mikhailov.otus.task7.domain.error.AuthorNotFoundException;
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
    public AuthorDto create(AuthorCreateDto author) {
        var newAuthor = new Author(author.name());
        var savedAuthor = repository.save(newAuthor);
        return new AuthorDto(savedAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto findById(Long id) {
        var existingAuthor = repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        return new AuthorDto(existingAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> findAll() {
        return repository.findAll().stream()
                .map(AuthorDto::new)
                .toList();
    }
}
