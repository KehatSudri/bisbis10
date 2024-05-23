package com.att.tdp.bisbis10.dto.OrderItemDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    @NotNull(message = "Dish ID is mandatory")
    private Long dishId;

    @NotNull(message = "Amount is mandatory")
    @Min(value = 1, message = "Amount must be at least 1")
    private Integer amount;
}