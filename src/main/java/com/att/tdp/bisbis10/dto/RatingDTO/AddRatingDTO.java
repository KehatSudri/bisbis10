package com.att.tdp.bisbis10.dto.RatingDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the Data Transfer Object (DTO) for adding a new rating to a restaurant
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRatingDTO {

    /**
     * The ID of the restaurant to be rated
     * It is mandatory and must not be null
     */
    @NotNull(message = "Restaurant ID is mandatory")
    private Long restaurantId;

    /**
     * The rating for the restaurant
     * It is mandatory and must be a number between 1 and 5
     */
    @NotNull(message = "Rating is mandatory")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Double rating;
}