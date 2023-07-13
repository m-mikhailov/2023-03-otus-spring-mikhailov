package ru.mikhailov.otus.task9.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task9.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task9.domain.dto.CommentDto;
import ru.mikhailov.otus.task9.service.CommentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Comments Controller Tests")
@WebMvcTest(CommentsController.class)
public class CommentsControllerTests {

    private final static List<CommentDto> EXISTING_COMMENTS = List.of(
            new CommentDto(1L, "Супер!"),
            new CommentDto(1L, "Класс!")
    );

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService service;

    @Test
    @DisplayName("Should return comments and list view")
    public void shouldReturnCommentsAndListView() throws Exception {
        given(service.findAllByBookId(anyLong()))
                .willReturn(EXISTING_COMMENTS);

        mvc.perform(get("/comments")
                .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comments/list"))
                .andExpect(model().attribute("comments", EXISTING_COMMENTS))
                .andExpect(model().attributeExists("bookId"));
    }

    @Test
    @DisplayName("Should add new comment and return redirect")
    public void shouldAddNewCommentAndReturnRedirect() throws Exception {
        mvc.perform(post("/comments")
                .param("text", "Круто")
                .param("bookId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/comments"))
                .andExpect(model().attributeExists("bookId"));

        var expected = new CommentCreateDto("Круто", 1L);

        verify(service).add(eq(expected));
    }

    @Test
    @DisplayName("Should delete comment and return redirect")
    public void shouldDeleteCommentAndReturnRedirect() throws Exception {
        mvc.perform(get("/comments/delete/{id}", "1")
                .param("bookId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/comments"))
                .andExpect(model().attributeExists("bookId"));

        verify(service).deleteById(eq(1L));
    }

}
