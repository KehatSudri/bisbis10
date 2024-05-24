package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.CreateOrderDTO;
import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is a service class for the Order entity
 * It uses OrderRepository, RestaurantRepository, and DishRepository to provide service operations for the Order entity
 * It includes methods to create orders
 */
@RequiredArgsConstructor
@Service
public class OrderService {

    /**
     * Repository for managing orders in the database
     */
    private final OrderRepository orderRepository;

    /**
     * Repository for managing restaurants in the database
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Repository for managing dishes in the database
     */
    private final DishRepository dishRepository;

    /**
     * Creates a new order for a specific restaurant
     * @param createOrderDTO the data transfer object containing the details of the order to be created
     * @return the ID of the created order
     */
    public UUID createOrder(CreateOrderDTO createOrderDTO) {
        Restaurant restaurant = restaurantRepository.findById(createOrderDTO.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + createOrderDTO.getRestaurantId()));

        Order order = new Order();
        order.setRestaurant(restaurant);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : createOrderDTO.getOrderItems()) {
            if (!dishRepository.existsById(orderItemDTO.getDishId())) {
                throw new ResourceNotFoundException("Dish not found with id " + orderItemDTO.getDishId());
            }

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