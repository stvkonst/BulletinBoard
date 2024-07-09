package controller;

import domain.Ad;
import domain.Subscription;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import service.SubscriptionService;

@RestController
@RequestMapping(value = "subscription")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionController {
         SubscriptionService subService;

    @PostMapping(value = "/save")
    public void save(@RequestBody Subscription sub) {
        subService.insert(sub);
    }

    @GetMapping(value = "/find/{id}")
    public Subscription findById(@PathVariable(name = "id") int id) {
        Subscription sub = subService.findById(id);
        return sub;
    }

    @PutMapping(value = "/update")
    public void update(@RequestBody Subscription sub) {
        subService.update(sub);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) {
        subService.deleteById(id);
    }
}
