package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CommentDto>> listBooks(@RequestParam("bookId") Long bookId) {
        return ResponseEntity.ok(service.findAllByBookId(bookId));
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentCreateDto comment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(comment));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
