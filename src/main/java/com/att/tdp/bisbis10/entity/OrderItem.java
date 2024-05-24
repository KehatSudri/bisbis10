package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * This is the Entity class for an OrderItem
 */
@Entity
@Data
@RequiredArgsConstructor
public class OrderItem {

    /**
     * The ID of the order item
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The amount of the dish in the order item
     */
    private int amount;

    /**
     * The order that the order item belongs to
     * It is mandatory and must not be null
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * The ID of the dish in the order item
     * It is mandatory and must not be null
     */
    @Column(name = "dish_id", nullable = false)
    private Long dishId;
}