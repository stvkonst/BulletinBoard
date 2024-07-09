package controller;

import domain.Author;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CrudService;

@Controller
@RequestMapping(value = "author")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    CrudService<Author> authorService;

    @PostMapping(value = "/save")
    public void save(@RequestBody Author author) {
        authorService.insert(author);
    }

    @GetMapping(value = "/find/{id}")
    @ResponseBody
    public Author findById(@PathVariable(name = "id") int id) {
        Author author = authorService.findById(id);
        return author;
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody Author author) {
        authorService.update(author);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        authorService.deleteById(id);
    }




}


