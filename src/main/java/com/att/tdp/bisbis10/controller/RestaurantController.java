package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RestaurantDTO.AddRestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantDTO.GetRestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantDTO.UpdateRestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;

/**
 * This is the controller for managing restaurants
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    /**
     * Service for managing restaurants
     */
    private final RestaurantService restaurantService;

    /**
     * Endpoint for getting a specific restaurant by its ID
     * @param id The ID of the restaurant
     * @return The requested restaurant
     */
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    /**
     * Endpoint for getting restaurants by cuisine or all restaurants if no cuisine is specified
     * @param cuisine The cuisine to filter by (optional)
     * @return A list of restaurants that match the filter
     */
    @GetMapping
    public ResponseEntity<List<GetRestaurantDTO>> getRestaurantsByCuisine(@RequestParam(required = false) String cuisine) {
        List<Restaurant> restaurants;
        if (cuisine != null) {
            restaurants = restaurantService.getRestaurantsByCuisine(cuisine);
        } else {
            restaurants = restaurantService.getAllRestaurants();
        }
        List<GetRestaurantDTO> restaurantDTOs = restaurants.stream()
                .map(restaurant -> new GetRestaurantDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getIsKosher(),
                        restaurant.getCuisines(),
                        restaurant.getAverageRating()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDTOs, HttpStatus.OK);
    }

    /**
     * Endpoint for adding a new restaurant
     * @param addRestaurantDTO The details of the restaurant to be added
     * @return HTTP status indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<Void> addRestaurant(@Valid @RequestBody AddRestaurantDTO addRestaurantDTO) {
        restaurantService.addRestaurant(addRestaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint for updating a restaurant
     * @param id The ID of the restaurant to be updated
     * @param updateRestaurantDTO The new details of the restaurant
     * @return HTTP status indicating the result of the operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @Valid @RequestBody UpdateRestaurantDTO updateRestaurantDTO) {
        restaurantService.updateRestaurant(id, updateRestaurantDTO.getCuisines());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a restaurant
     * @param id The ID of the restaurant to be deleted
     * @return HTTP status indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}