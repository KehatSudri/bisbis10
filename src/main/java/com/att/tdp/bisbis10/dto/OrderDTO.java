package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.OrderItem;

import java.util.List;

public class OrderDTO {
    private Long restaurantId;
    private List<OrderItem> orderItems;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}