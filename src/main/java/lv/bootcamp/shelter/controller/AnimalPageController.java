package lv.bootcamp.shelter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lv.bootcamp.shelter.form.AnimalForm;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/animals/new")
    public String newAnimalForm(Model model) {
        model.addAttribute("form", new AnimalForm(null, null, null, null, null, null));
        return "animals-new"; // templates/animals-new.html
    }

    @PostMapping("/animals")
    public String createAnimal(@Valid @ModelAttribute("form") AnimalForm form,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // show the same form again - entered values and error messages stay on the page
            return "animals-new";
        }
        animalService.createFromForm(form);
        return "redirect:/animals";
    }
}