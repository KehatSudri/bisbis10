package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * This is the Entity class for an Order
 */
@Entity(name = "orders")
@Data
@RequiredArgsConstructor
public class Order {

    /**
     * The ID of the order
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The restaurant that the order is placed at
     * It is mandatory and must not be null
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    /**
     * The list of order items in the order
     * When the order is deleted, all its order items are also deleted (CascadeType.ALL)
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}