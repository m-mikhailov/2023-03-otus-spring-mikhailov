package ru.mikhailov.otus.task10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task10.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task10.domain.dto.CommentDto;
import ru.mikhailov.otus.task10.service.CommentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Comments Controller Tests")
@WebMvcTest(CommentsController.class)
public class CommentsControllerTests {

    private final static List<CommentDto> EXISTING_COMMENTS = List.of(
            new CommentDto(1L, "Супер!"),
            new CommentDto(1L, "Класс!")
    );

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService service;

    @Test
    @DisplayName("Should return all comments by bookId")
    public void shouldReturnAllBookComments() throws Exception {
        given(service.findAllByBookId(anyLong()))
                .willReturn(EXISTING_COMMENTS);

        mvc.perform(get("/comments")
                        .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_COMMENTS)));
    }

    @Test
    @DisplayName("Should add new comment")
    public void shouldAddNewComment() throws Exception {

        var commentCreateDto = new CommentCreateDto("Круто", 1L);
        var commentDto = new CommentDto(1L, "Круто");

        given(service.add(any(CommentCreateDto.class)))
                .willReturn(commentDto);

        mvc.perform(post("/comments")
                        .param("bookId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(commentDto)));
    }

    @Test
    @DisplayName("Should delete comment")
    public void shouldDeleteComment() throws Exception {
        mvc.perform(delete("/comments/{id}", "1"))
                .andExpect(status().isNoContent());

        verify(service).deleteById(eq(1L));
    }

}
