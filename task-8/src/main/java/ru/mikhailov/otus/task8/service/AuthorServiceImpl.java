package ru.mikhailov.otus.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task8.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task8.domain.dto.AuthorDto;
import ru.mikhailov.otus.task8.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task8.domain.model.Author;
import ru.mikhailov.otus.task8.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public AuthorDto create(AuthorCreateDto author) {
        var newAuthor = new Author(author.name());
        var savedAuthor = repository.save(newAuthor);
        return new AuthorDto(savedAuthor);
    }

    @Override
    public AuthorDto findById(String id) {
        var existingAuthor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.AUTHOR_MESSAGE_FORMAT, id));
        return new AuthorDto(existingAuthor);
    }

    @Override
    public List<AuthorDto> findAll() {
        return repository.findAll().stream()
                .map(AuthorDto::new)
                .toList();
    }
}
