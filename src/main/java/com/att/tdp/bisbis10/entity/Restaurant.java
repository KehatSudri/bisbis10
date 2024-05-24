package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * This is the Entity class for a Restaurant
 */
@Entity
@Data
@RequiredArgsConstructor
public class Restaurant {

    /**
     * The ID of the restaurant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the restaurant
     */
    private String name;

    /**
     * The average rating of the restaurant
     */
    private Double averageRating;

    /**
     *The kosher status of the restaurant
     */
    private Boolean isKosher;

    /**
     * The list of cuisines the restaurant offers
     */
    @ElementCollection
    private List<String> cuisines;

    /**
     * The list of dishes offered by the restaurant
     * When the restaurant is deleted, all its dishes are also deleted (CascadeType.ALL)
     */
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    /**
     * The list of orders placed at the restaurant
     */
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    /**
     * The list of ratings for the restaurant
     */
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Rating> ratings;


}