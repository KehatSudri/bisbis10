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

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // if cuisines is not null, call getRestaurantsByCuisine(cuisine) method from restaurantService else call getAllRestaurants() method from restaurantService
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
                        restaurant.getAverageRating(),
                        restaurant.getIsKosher(),
                        restaurant.getCuisines()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(restaurantDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addRestaurant(@Valid @RequestBody AddRestaurantDTO addRestaurantDTO) {
        restaurantService.addRestaurant(addRestaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @Valid @RequestBody UpdateRestaurantDTO updateRestaurantDTO) {
        restaurantService.updateRestaurant(id, updateRestaurantDTO.getCuisines());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}