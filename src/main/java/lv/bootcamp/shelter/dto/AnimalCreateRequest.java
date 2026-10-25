package lv.bootcamp.shelter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lv.bootcamp.shelter.model.AnimalType;

/**
 * JSON request body for creating a new animal via the REST API.
 * Status is not included; all new animals start as AVAILABLE.
 */
public record AnimalCreateRequest(

        @Schema(description = "Animal name", example = "Milo")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Kind of animal", example = "CAT")
        @NotNull(message = "Type is required")
        AnimalType type,

        @Schema(description = "Breed, optional", example = "Siamese")
        String breed,

        @Schema(description = "Age in years, 0 or more", example = "3", minimum = "0")
        @Min(value = 0, message = "Age cannot be negative")
        Integer age,

        @Schema(description = "Short description of the animal", example = "Calm and friendly, likes to sleep on the sofa")
        String description,

        @Schema(description = "Image filename from /images/animals or a full URL. Empty means default picture",
                example = "Luna.jpeg")
        String imageUrl
) {}