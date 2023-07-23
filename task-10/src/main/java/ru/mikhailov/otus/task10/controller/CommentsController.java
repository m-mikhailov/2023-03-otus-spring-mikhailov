package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mikhailov.otus.task10.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task10.domain.dto.CommentDto;
import ru.mikhailov.otus.task10.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService service;

    @GetMapping("/comments")
    public List<CommentDto> listBooks(@RequestParam("bookId") Long bookId) {
        return service.findAllByBookId(bookId);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@RequestBody CommentCreateDto comment) {
        return service.add(comment);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}
