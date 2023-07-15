package ru.mikhailov.otus.task9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task9.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task9.domain.dto.AuthorDto;
import ru.mikhailov.otus.task9.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task9.domain.model.Author;
import ru.mikhailov.otus.task9.repository.AuthorRepository;

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
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.AUTHOR_MESSAGE_FORMAT, id));
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
