package ru.mikhailov.otus.task9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mikhailov.otus.task9.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task9.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorService service;

    @GetMapping("/newauthor")
    public String newAuthor() {
        return "authors/add";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        var authors = service.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    @PostMapping("/authors")
    public String addAuthor(AuthorCreateDto author) {
        service.create(author);
        return "redirect:/authors";
    }

}
