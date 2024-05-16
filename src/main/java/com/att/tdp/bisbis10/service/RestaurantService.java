package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        System.out.println("Getting all restaurants");
        return restaurantRepository.findAll();
    }
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }
    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findRestaurantsByCuisinesContains(cuisine);
    }
    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
    public void updateRestaurant(Long id, Restaurant restaurantData) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
        existingRestaurant.setCuisines(restaurantData.getCuisines());
        restaurantRepository.save(existingRestaurant);
    }
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
        restaurantRepository.deleteById(id);
    }
}