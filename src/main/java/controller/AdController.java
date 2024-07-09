package controller;

import domain.Ad;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import service.AdService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "ad")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdController {
    AdService adService;

    @PostMapping(value = "/save") //test.
    public void save(@RequestBody Ad ad) {
        adService.insert(ad);
    }


    @GetMapping(value = "/find-all-by-rubric-ids/{ids}") //test.
    public List<Ad> findAll(@PathVariable(name = "ids") List<Integer> ids) {
        return adService.showByRubricIds(ids);
    }


    @GetMapping(value = "/find-all-by-author/{id}") //test
    public List<Ad> findAllByAuthorId(@PathVariable(name = "id") int id) {
        return adService.findAllByAuthorId(id);
    }


    @GetMapping(value = "/find-all-by-word/{word}") //test
    public List<Ad> findAllByWord(@PathVariable(name = "word") String word) {
        return adService.findAllByNameContains(word);
    }


    @GetMapping(value = "/find-all/{page}/{size}") //test
    public List<Ad> findAll(@PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
        return adService.findAllPage(page, size);
    }


    @GetMapping(value = "/find-all-by-date/{date}") //test.
    public List<Ad> findAllByDate(@PathVariable(name = "date")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        return adService.findAllByPublicationDate(date);
    }


    @GetMapping(value = "/find/{id}") //test.
    public Ad findById(@PathVariable(name = "id") int id) {
        return adService.findById(id);
    }


    @PutMapping(value = "/update") //test.
    public void update(@RequestBody Ad ad) {
        adService.update(ad);
    }


    @DeleteMapping(value = "/delete/{id}")  //test.
    public void delete(@PathVariable(name = "id") int id) {
        adService.deleteById(id);
    }


    @DeleteMapping(value = "/delete-all/{id}") //test.
    public void deleteAll(@PathVariable(name = "id") int id) {
        adService.deleteAllByAuthorId(id);
    }



}
