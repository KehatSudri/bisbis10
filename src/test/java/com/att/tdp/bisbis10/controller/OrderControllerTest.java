package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.CreateOrderDTO;
import com.att.tdp.bisbis10.dto.OrderItemDTO.OrderItemDTO;
import com.att.tdp.bisbis10.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

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
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setRestaurantId(1L);
        OrderItemDTO orderItemDTO = new OrderItemDTO(1L, 2);
        createOrderDTO.setOrderItems(Collections.singletonList(orderItemDTO));

        UUID uuid = UUID.randomUUID();

        when(orderService.createOrder(createOrderDTO)).thenReturn(uuid);

        ResponseEntity<Map<String, UUID>> response = orderController.createOrder(createOrderDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uuid, response.getBody().get("orderId"));
        verify(orderService, times(1)).createOrder(createOrderDTO);
    }
}