package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This is the Entity class for a Dish
 */
@Entity
@Data
@RequiredArgsConstructor
public class Dish {

    /**
     * The ID of the dish
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the dish
     */
    private String name;

    /**
     * The description of the dish
     */
    private String description;

    /**
     * The price of the dish
     */
    private double price;

    /**
     * The restaurant that offers the dish
     * It is mandatory and must not be null
     * When the restaurant is deleted, all its dishes are also deleted (OnDeleteAction.CASCADE)
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;
}