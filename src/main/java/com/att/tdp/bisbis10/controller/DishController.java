
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<Dish>> getDishesByRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        List<Dish> dishes = dishService.getDishesByRestaurant(restaurantId);
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addDish(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody AddDishDTO addDishDTO) {
        dishService.addDish(restaurantId, addDishDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{dishId}")
    public ResponseEntity<Void> updateDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId, @Valid @RequestBody UpdateDishDTO updateDishDTO) {
        dishService.updateDish(restaurantId, dishId, updateDishDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable("restaurantId") Long restaurantId, @PathVariable("dishId") Long dishId) {
        dishService.deleteDish(restaurantId, dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}


