package ru.mikhailov.otus.task9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mikhailov.otus.task9.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task9.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task9.service.BookService;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final BookService service;

    @GetMapping("/books")
    public String listBooks(Model model) {
        var books = service.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/newbook")
    public String newBook() {
        return "books/add";
    }

    @PostMapping("/books")
    public String addBook(BookCreateDto book) {
        service.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        var book = service.findById(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/books/{id}")
    public String updateBook(@PathVariable("id") Long id, BookUpdateDto book) {
        service.update(book);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/books";
    }
}
