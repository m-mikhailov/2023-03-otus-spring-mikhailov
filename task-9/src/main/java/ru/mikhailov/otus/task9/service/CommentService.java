package ru.mikhailov.otus.task9.service;

import ru.mikhailov.otus.task9.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task9.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void deleteById(Long id);

    CommentDto add(CommentCreateDto comment);

    List<CommentDto> findAllByBookId(Long bookId);

}
