package com.att.tdp.bisbis10.service;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Order order;
    private Restaurant restaurant;
    private OrderItem orderItem;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant();
        orderItem = new OrderItem();
        order = new Order(restaurant, Collections.singletonList(orderItem));
    }

    @Test
    public void testCreateOrder() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(1L, Collections.singletonList(orderItem));

        assertEquals(order, createdOrder);
    }

    @Test
    public void testCreateOrderThrowsResourceNotFoundException() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.createOrder(1L, Collections.singletonList(orderItem)));
    }
}