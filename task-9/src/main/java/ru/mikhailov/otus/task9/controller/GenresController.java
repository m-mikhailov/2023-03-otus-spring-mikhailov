package ru.mikhailov.otus.task9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mikhailov.otus.task9.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task9.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task9.service.GenreService;

@Controller
@RequiredArgsConstructor
public class GenresController {

    private final GenreService service;

    @GetMapping("/newgenre")
    public String newGenre() {
        return "genres/add";
    }

    @GetMapping("/genres")
    public String listGenres(Model model) {
        var genres = service.findAll();
        model.addAttribute("genres", genres);
        return "genres/list";
    }

    @PostMapping("/genres")
    public String addGenre(GenreCreateDto genre) {
        service.save(genre);
        return "redirect:/genres";
    }

}
