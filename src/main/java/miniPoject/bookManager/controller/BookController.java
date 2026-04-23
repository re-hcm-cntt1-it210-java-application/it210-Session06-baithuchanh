package miniPoject.bookManager.controller;


import miniPoject.bookManager.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "Java Core", "A", 250000));
        books.add(new Book(2, "Spring MVC", "B", 350000));
        books.add(new Book(3, "ReactJS", "C", 400000));
        books.add(new Book(4, "NodeJS", "D", 200000));
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", books);
        model.addAttribute("title", "Danh sách sách");
        return "books";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Book found = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("book", found);
        model.addAttribute("title", "Chi tiết sách");
        return "detail";
    }
}