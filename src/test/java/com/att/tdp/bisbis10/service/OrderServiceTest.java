package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.CreateOrderDTO;
import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(java.util.Optional.of(restaurant));

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setDishId(1L);
        orderItemDTO.setAmount(2);

        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setRestaurantId(1L);
        createOrderDTO.setOrderItems(Collections.singletonList(orderItemDTO));

        orderService.createOrder(createOrderDTO);

        verify(orderRepository, times(1)).save(any(Order.class));
    }
}