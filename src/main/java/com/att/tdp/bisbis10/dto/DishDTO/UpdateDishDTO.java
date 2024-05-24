package com.att.tdp.bisbis10.dto.DishDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the Data Transfer Object (DTO) for updating an existing dish
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDishDTO {

    /**
     * The updated description of the dish
     * It is mandatory and must not be blank
     */
    @NotBlank(message = "Description is mandatory")
    private String description;

    /**
     * The updated price of the dish
     * It is mandatory and must be a positive number
     */
    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than 0")
    private double price;
}