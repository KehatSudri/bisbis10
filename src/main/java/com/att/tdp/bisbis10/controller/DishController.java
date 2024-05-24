package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishDTO.AddDishDTO;
import com.att.tdp.bisbis10.dto.DishDTO.UpdateDishDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.entity.Dish;

import java.util.List;

/**
 * This is the controller for managing dishes in a restaurant
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    /**
     * Service for managing dishes
     */
    private final DishService dishService;

    /**
     * Endpoint for getting all dishes in a restaurant
     * @param restaurantId The ID of the restaurant
     * @return A list of dishes in the restaurant
     */
    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    /**
     * Endpoint for adding a new dish to a restaurant
     * @param restaurantId The ID of the restaurant
     * @param addDishDTO The details of the dish to be added
     * @return HTTP status indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody AddDishDTO addDishDTO) {
        dishService.addDish(restaurantId, addDishDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating a dish in a restaurant
     * @param restaurantId The ID of the restaurant
     * @param dishId The ID of the dish to be updated
     * @param updateDishDTO The new details of the dish
     * @return HTTP status indicating the result of the operation
     */
    @PutMapping("/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId, @Valid @RequestBody UpdateDishDTO updateDishDTO) {
        dishService.updateDish(restaurantId, dishId, updateDishDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a dish from a restaurant
     * @param restaurantId The ID of the restaurant
     * @param dishId The ID of the dish to be deleted
     * @return HTTP status indicating the result of the operation
     */
    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId) {
        dishService.deleteDish(restaurantId, dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}