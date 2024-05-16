
package com.att.tdp.bisbis10.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.entity.Dish;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId,@Valid @RequestBody Dish dish) {
        dishService.addDish(restaurantId, dish);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId, @Valid @RequestBody Dish dish) {
        dishService.updateDish(restaurantId, dishId, dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId) {
        dishService.deleteDish(restaurantId, dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
}


