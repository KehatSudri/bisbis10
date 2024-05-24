package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is the Data Transfer Object (DTO) for creating a new order
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {

    /**
     * The ID of the restaurant where the order is placed
     * It is mandatory and must not be null
     */
    @NotNull(message = "Restaurant ID is mandatory")
    private Long restaurantId;

    /**
     * The list of items in the order
     * It is mandatory and must not be empty
     */
    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemDTO> orderItems;
}