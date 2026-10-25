package lv.bootcamp.shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lv.bootcamp.shelter.dto.AnimalCreateRequest;
import lv.bootcamp.shelter.dto.AnimalResponse;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for shelter animal endpoints.
 * Returns JSON — does not render HTML pages.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animals")
@Tag(name = "Animals", description = "List, create and adopt shelter animals")
public class AnimalApiController {

    private final AnimalService animalService;

    @Operation(summary = "List all animals",
            description = "Returns every animal in the shelter, both available and already adopted.")
    @ApiResponse(responseCode = "200", description = "List of animals (empty list if there are none)")
    @GetMapping
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }

    @Operation(summary = "Get one animal by id",
            description = "Looks up a single animal. Unknown ids return 404 with an empty body.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal found"),
            @ApiResponse(responseCode = "404", description = "No animal with this id", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> findById(
            @Parameter(description = "Animal id", example = "1") @PathVariable Long id) {
        return animalService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "List adopted animals",
            description = "Returns only animals whose status is ADOPTED.")
    @ApiResponse(responseCode = "200", description = "List of adopted animals (may be empty)")
    @GetMapping("/adopted")
    public List<AnimalResponse> findAdopted() {
        return animalService.findAdopted();
    }

    @Operation(summary = "Register a new animal",
            description = "Creates an animal with status AVAILABLE. If imageUrl is empty, "
                    + "a default picture for the animal type is used.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Animal created, the saved animal is returned"),
            @ApiResponse(responseCode = "400",
                    description = "Validation failed: name blank, type missing or age negative",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse create(@RequestBody @Valid AnimalCreateRequest request) {
        return animalService.create(request);
    }

    @Operation(summary = "Adopt an animal",
            description = "Changes the status to ADOPTED and records today's date in adoptionNote. "
                    + "An animal can be adopted only once.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal adopted"),
            @ApiResponse(responseCode = "404", description = "No animal with this id", content = @Content),
            @ApiResponse(responseCode = "409", description = "Animal is already adopted",
                    content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/{id}/adopt")
    public ResponseEntity<AnimalResponse> adopt(
            @Parameter(description = "Animal id", example = "1") @PathVariable Long id) {
        return animalService.adopt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleAlreadyAdopted(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}