package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * This is the Entity class for a Rating
 */
@Entity
@Data
@RequiredArgsConstructor
public class Rating {

    /**
     * The ID of the rating
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The value of the rating
     */
    private double value;

    /**
     * The restaurant that the rating is for
     * It is mandatory and must not be null
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}