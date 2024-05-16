package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Order createOrder(Long restaurantId, List<OrderItem> orderItems) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Order order = new Order();
        order.setRestaurant(restaurant);

        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
            if (orderItem.getDishId() == null) {
                throw new RuntimeException("DishId cannot be null");
            }
        });

        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }
}