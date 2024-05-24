package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDTO.AddRestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }

    public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        return restaurantRepository.findRestaurantsByCuisinesContains(cuisine);
    }

    public void addRestaurant(AddRestaurantDTO addRestaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(addRestaurantDTO.getName());
        restaurant.setIsKosher(addRestaurantDTO.getIsKosher());
        restaurant.setCuisines(addRestaurantDTO.getCuisines());
        restaurantRepository.save(restaurant);
    }

    public void updateRestaurant(Long id, List<String> cuisines) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
        existingRestaurant.setCuisines(cuisines);
        restaurantRepository.save(existingRestaurant);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
        restaurantRepository.deleteById(id);
    }
}