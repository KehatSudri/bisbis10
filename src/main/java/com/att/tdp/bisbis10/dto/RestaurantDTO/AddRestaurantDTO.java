package com.att.tdp.bisbis10.dto.RestaurantDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRestaurantDTO {
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "isKosher is required")
    private Boolean isKosher;

    @NotEmpty(message = "Cuisines is required")
    private List<String> cuisines;
}