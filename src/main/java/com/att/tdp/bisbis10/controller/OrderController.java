package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.CreateOrderDTO;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, UUID>> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        UUID orderId = orderService.createOrder(createOrderDTO);
        Map<String, UUID> response = Collections.singletonMap("orderId", orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}