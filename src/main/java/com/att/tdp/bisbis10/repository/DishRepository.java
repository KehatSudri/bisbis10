package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a repository interface for the Dish entity
 * It extends JpaRepository to provide CRUD operations for the Dish entity
 * It includes a custom method to find dishes by restaurant id
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    /**
     * Finds and returns a list of Dish entities associated with a specific restaurant
     * @param restaurantId the id of the restaurant
     * @return a list of Dish entities associated with the restaurant
     */
    List<Dish> findByRestaurantId(Long restaurantId);

}