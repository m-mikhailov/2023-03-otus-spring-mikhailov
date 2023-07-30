package ru.mikhailov.otus.task11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task11.domain.dto.CommentDto;
import ru.mikhailov.otus.task11.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService service;

    @GetMapping( "/comments")
    public Flux<CommentDto> listBooks(@RequestParam("bookId") String bookId) {
        return service.findAllByBookId(bookId);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CommentDto> addComment(@RequestBody CommentCreateDto comment) {
        return service.add(comment);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return service.deleteById(id);
    }

}
