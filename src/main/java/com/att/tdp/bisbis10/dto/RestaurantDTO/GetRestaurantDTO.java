package com.att.tdp.bisbis10.dto.RestaurantDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This is the Data Transfer Object (DTO) for retrieving restaurant details
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantDTO {

    /**
     * The ID of the restaurant
     */
    private Long id;

    /**
     * The name of the restaurant
     */
    private String name;

    /**
     * The kosher status of the restaurant
     */
    private Boolean isKosher;

    /**
     * The list of cuisines the restaurant offers
     */
    private List<String> cuisines;

    /**
     * The average rating of the restaurant
     */
    private Double averageRating;
}