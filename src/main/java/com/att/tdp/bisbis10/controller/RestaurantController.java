package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // if cuisines is not null, call getRestaurantsByCuisine(cuisine) method from restaurantService else call getAllRestaurants() method from restaurantService
    @GetMapping
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@RequestParam(required = false) String cuisine) {
        List<Restaurant> restaurants;
        if (cuisine != null) {
            restaurants = restaurantService.getRestaurantsByCuisine(cuisine);
        } else {
            restaurants = restaurantService.getAllRestaurants();
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(id, restaurant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}