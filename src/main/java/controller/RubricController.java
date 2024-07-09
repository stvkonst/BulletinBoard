package controller;


import domain.Ad;
import domain.Rubric;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CrudService;

@RestController
@RequestMapping(value = "rubric")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RubricController {
        CrudService<Rubric> rubricService;

        @PostMapping(value = "/save")
        public void save(@RequestBody Rubric rubric) {
                rubricService.insert(rubric);
        }

        @GetMapping(value = "/find/{id}")
        @ResponseBody
        public Rubric findById(@PathVariable(name = "id") int id) {
                Rubric rubric = rubricService.findById(id);
                return rubric;
        }

        @PutMapping(value = "/update")
        public void update(@RequestBody Rubric rubric) {
                rubricService.update(rubric);
        }

        @DeleteMapping(value = "/delete/{id}")
        public void delete(@PathVariable(name = "id") int id) {
                rubricService.deleteById(id);
        }
}
