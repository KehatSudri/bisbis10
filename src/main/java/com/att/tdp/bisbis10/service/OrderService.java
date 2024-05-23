package com.att.tdp.bisbis10.service;


import com.att.tdp.bisbis10.dto.CreateOrderDTO;
import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;


    public UUID createOrder(CreateOrderDTO createOrderDTO) {
        Restaurant restaurant = restaurantRepository.findById(createOrderDTO.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + createOrderDTO.getRestaurantId()));

        Order order = new Order();
        order.setRestaurant(restaurant);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : createOrderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDishId(orderItemDTO.getDishId());
            orderItem.setAmount(orderItemDTO.getAmount());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        return order.getId();
    }
}