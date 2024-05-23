package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    @NotNull(message = "Restaurant ID is mandatory")
    private Long restaurantId;

    @NotEmpty(message = "Order must have at least one item")
    private List<OrderItemDTO> orderItems;
}