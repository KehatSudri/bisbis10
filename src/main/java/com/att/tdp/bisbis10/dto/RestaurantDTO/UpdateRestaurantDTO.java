package com.att.tdp.bisbis10.dto.RestaurantDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UpdateRestaurantDTO {
    @NotEmpty(message = "Cuisines is required")
    private List<String> cuisines;

}