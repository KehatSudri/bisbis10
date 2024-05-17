package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.OrderDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRestaurantId(1L);
        OrderItem orderItem = new OrderItem(1L, 2);
        orderDTO.setOrderItems(Collections.singletonList(orderItem));

        Order order = new Order();
        order.setId(1L);
        order.setRestaurant(null); // Assuming the restaurant is not known in the test context
        order.setOrderItems(Collections.singletonList(orderItem));

        when(orderService.createOrder(orderDTO.getRestaurantId(), orderDTO.getOrderItems())).thenReturn(order);

        ResponseEntity<Order> response = orderController.createOrder(orderDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).createOrder(orderDTO.getRestaurantId(), orderDTO.getOrderItems());
    }
}