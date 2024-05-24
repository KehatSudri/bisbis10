package com.att.tdp.bisbis10.dto.RestaurantDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is the Data Transfer Object (DTO) for adding a new restaurant
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRestaurantDTO {

    /**
     * The name of the restaurant
     * It is mandatory and must not be null
     */
    @NotNull(message = "Name is required")
    private String name;

    /**
     * The kosher status of the restaurant
     * It is mandatory and must not be null
     */
    @NotNull(message = "isKosher is required")
    private Boolean isKosher;

    /**
     * The list of cuisines the restaurant offers
     * It is mandatory and must not be empty
     */
    @NotEmpty(message = "Cuisines is required")
    private List<String> cuisines;
}