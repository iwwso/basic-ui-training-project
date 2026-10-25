package lv.bootcamp.shelter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Page controller for the Thymeleaf UI.
 * Returns view names (templates/*.html), not JSON - the REST API stays in AnimalApiController.
 *
 * @author Karyna Litvinova
 */
@Controller
public class AnimalPageController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }
}