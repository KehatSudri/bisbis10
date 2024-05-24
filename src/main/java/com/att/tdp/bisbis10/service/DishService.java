package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDTO.AddDishDTO;
import com.att.tdp.bisbis10.dto.DishDTO.UpdateDishDTO;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.BadRequestException;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a service class for the Dish entity
 * It uses DishRepository and RestaurantRepository to provide service operations for the Dish entity
 * It includes methods to add, update, delete and retrieve dishes
 */
@RequiredArgsConstructor
@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Adds a new dish to a specific restaurant
     * @param restaurantId the id of the restaurant
     * @param addDishDTO the data transfer object containing the details of the dish to be added
     */
    public void addDish(Long restaurantId, AddDishDTO addDishDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));

        Dish dish = new Dish();
        dish.setName(addDishDTO.getName());
        dish.setDescription(addDishDTO.getDescription());
        dish.setPrice(addDishDTO.getPrice());
        dish.setRestaurant(restaurant);

        dishRepository.save(dish);
    }

    /**
     * Updates an existing dish of a specific restaurant
     * @param restaurantId the id of the restaurant
     * @param dishId the id of the dish to be updated
     * @param updateDishDTO the data transfer object containing the updated details of the dish
     */
    public void updateDish(Long restaurantId, Long dishId, UpdateDishDTO updateDishDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));

        Dish existingDish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id " + dishId));

        if (!existingDish.getRestaurant().equals(restaurant)) {
            throw new BadRequestException("Dish does not belong to the specified restaurant");
        }

        existingDish.setDescription(updateDishDTO.getDescription());
        existingDish.setPrice(updateDishDTO.getPrice());

        dishRepository.save(existingDish);
    }

    /**
     * Deletes a specific dish of a specific restaurant
     * @param restaurantId the id of the restaurant
     * @param dishId the id of the dish to be deleted
     */
    public void deleteDish(Long restaurantId, Long dishId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));

        Dish existingDish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id " + dishId));

        if (!existingDish.getRestaurant().equals(restaurant)) {
            throw new BadRequestException("Dish does not belong to the specified restaurant");
        }

        dishRepository.delete(existingDish);
    }

    /**
     * Retrieves and returns a list of Dish entities associated with a specific restaurant
     * @param restaurantId the id of the restaurant
     * @return a list of Dish entities associated with the restaurant
     */
    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        return dishRepository.findByRestaurantId(restaurantId);
    }
}