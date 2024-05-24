package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDTO.AddRestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a service class for the Restaurant entity
 * It uses RestaurantRepository to provide service operations for the Restaurant entity
 * It includes methods to add, update, delete and retrieve restaurants
 */
@RequiredArgsConstructor
@Service
public class RestaurantService {

    /**
     * Repository for managing restaurants in the database
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Retrieves and returns a list of all Restaurant entities
     * @return a list of all Restaurant entities
     */
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * Retrieves and returns a Restaurant entity by its id
     * @param id the id of the restaurant
     * @return the Restaurant entity with the given id
     */
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }

    /**
     * Retrieves and returns a list of Restaurant entities by cuisine
     * @param cuisine the cuisine of the restaurants
     * @return a list of Restaurant entities with the given cuisine
     */
    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findRestaurantsByCuisinesContains(cuisine);
    }

    /**
     * Adds a new restaurant
     * @param addRestaurantDTO the data transfer object containing the details of the restaurant to be added
     */
    public void addRestaurant(AddRestaurantDTO addRestaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(addRestaurantDTO.getName());
        restaurant.setIsKosher(addRestaurantDTO.getIsKosher());
        restaurant.setCuisines(addRestaurantDTO.getCuisines());
        restaurantRepository.save(restaurant);
    }

    /**
     * Updates the cuisines of a restaurant
     * @param id the id of the restaurant to be updated
     * @param cuisines the new list of cuisines
     */
    public void updateRestaurant(Long id, List<String> cuisines) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
        existingRestaurant.setCuisines(cuisines);
        restaurantRepository.save(existingRestaurant);
    }

    /**
     * Deletes a restaurant by its id
     * @param id the id of the restaurant to be deleted
     */
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
        restaurantRepository.deleteById(id);
    }
}