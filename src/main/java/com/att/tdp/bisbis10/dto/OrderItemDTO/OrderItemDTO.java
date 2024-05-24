package com.att.tdp.bisbis10.dto.OrderItemDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the Data Transfer Object (DTO) for an item in an order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    /**
     * The ID of the dish in the order
     * It is mandatory and must not be null
     */
    @NotNull(message = "Dish ID is mandatory")
    private Long dishId;

    /**
     * The amount of the dish in the order
     * It is mandatory and must be at least 1
     */
    @NotNull(message = "Amount is mandatory")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;
}