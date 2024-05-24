package com.att.tdp.bisbis10.dto.RestaurantDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is the Data Transfer Object (DTO) for updating a restaurant's details
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurantDTO {

    /**
     * The ID of the restaurant to be updated
     * It is mandatory and must not be null
     */
    private Long id;

    /**
     * The new name of the restaurant
     * It is optional and can be null
     */
    private String name;

    /**
     * The new kosher status of the restaurant
     * It is optional and can be null
     */
    private Boolean isKosher;

    /**
     * The new list of cuisines the restaurant offers
     * It is optional and can be null
     */
    @NotNull(message = "cuisines is required")
    private List<String> cuisines;
}