package com.att.tdp.bisbis10.dto.RestaurantDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantDTO {
    private Long id;
    private String name;
    private double averageRating;
    private Boolean isKosher;
    private List<String> cuisines;
}