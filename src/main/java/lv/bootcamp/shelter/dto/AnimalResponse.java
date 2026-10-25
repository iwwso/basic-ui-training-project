package lv.bootcamp.shelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lv.bootcamp.shelter.model.AnimalStatus;
import lv.bootcamp.shelter.model.AnimalType;

/**
 * Response body for a single animal returned by the API.
 * {@code adoptionNote} is filled once the animal is adopted, otherwise it is null.
 */
public record AnimalResponse(
        @Schema(description = "Animal id", example = "1")
        Long id,
        @Schema(description = "Animal name", example = "Milo")
        String name,
        @Schema(description = "Kind of animal", example = "CAT")
        AnimalType type,
        @Schema(description = "Breed, may be null", example = "Siamese")
        String breed,
        @Schema(description = "Age in years, may be null", example = "3")
        Integer age,
        @Schema(description = "Short description", example = "Calm and friendly, likes to sleep on the sofa")
        String description,
        @Schema(description = "Adoption status", example = "AVAILABLE")
        AnimalStatus status,
        @Schema(description = "Path or URL of the photo", example = "/images/animals/Luna.jpeg")
        String imageUrl,
        @Schema(description = "Set when adopted, e.g. 'adopted on 2026-06-01', otherwise null",
                example = "adopted on 2026-06-01", nullable = true)
        String adoptionNote
) {}