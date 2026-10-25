package lv.bootcamp.shelter.controller;

import lombok.RequiredArgsConstructor;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Page controller for the Thymeleaf UI.
 * Returns view names (templates/*.html), not JSON - the REST API stays in AnimalApiController.
 *
 * @author Karyna Litvinova
 */
@Controller
@RequiredArgsConstructor
public class AnimalPageController {

    private final AnimalService animalService;

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    @GetMapping("/animals")
    public String listAnimals(Model model) {
        model.addAttribute("animals", animalService.findAll());
        return "animals"; // templates/animals.html
    }
}