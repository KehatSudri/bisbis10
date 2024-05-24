package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a repository interface for the Restaurant entity
 * It extends JpaRepository to provide CRUD operations for the Restaurant entity
 * It includes a custom method to find restaurants by cuisine
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Finds and returns a list of Restaurant entities that offer a specific cuisine
     * @param cuisine the type of cuisine
     * @return a list of Restaurant entities that offer the specified cuisine
     */
    List<Restaurant> findRestaurantsByCuisinesContains(String cuisine);

}