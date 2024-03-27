package controller;

import domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CrudService;

@Controller
@RequestMapping(value = "authors")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    CrudService<Author> authorService;

    @PostMapping(value = "/author")
    public void save(@RequestBody Author author) {
        authorService.insert(author);
    }

    @GetMapping(value = "/author/{id}")
    @ResponseBody
    public Author findById(@PathVariable(name = "id") int id) {
        Author author = authorService.findById(id);
        return author;
    }
}
