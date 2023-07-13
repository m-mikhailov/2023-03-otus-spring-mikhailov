package ru.mikhailov.otus.task9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.mikhailov.otus.task9.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task9.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService service;

    @GetMapping("/comments")
    public String listBooks(@RequestParam("bookId") Long bookId,
                            Model model) {
        var comments = service.findAllByBookId(bookId);
        model.addAttribute("comments", comments);
        model.addAttribute("bookId", bookId);
        return "comments/list";
    }

    @PostMapping("/comments")
    public String addComment(CommentCreateDto comment,
                             RedirectAttributes redirectAttributes) {
        service.add(comment);
        redirectAttributes.addAttribute("bookId", comment.bookId());
        return "redirect:/comments";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id,
                             @RequestParam("bookId") Long bookId,
                             RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/comments";
    }

}
