package ru.mikhailov.otus.task11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task11.domain.dto.AuthorDto;
import ru.mikhailov.otus.task11.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task11.domain.model.Author;
import ru.mikhailov.otus.task11.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public Mono<AuthorDto> create(AuthorCreateDto author) {
        var newAuthor = new Author(author.name());
        return repository.save(newAuthor)
                .map(AuthorDto::new);
    }

    @Override
    public Mono<AuthorDto> findById(String id) {
        return repository.findById(id)
                .map(AuthorDto::new)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(EntityNotFoundException.AUTHOR_MESSAGE_FORMAT, id)));
    }

    @Override
    public Flux<AuthorDto> findAll() {
        return repository.findAll()
                .map(AuthorDto::new);
    }
}
