package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is a repository interface for the Rating entity
 * It extends JpaRepository to provide CRUD operations for the Rating entity
 * It includes a custom method to find ratings by restaurant
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    /**
     * Finds and returns a list of Rating entities associated with a specific restaurant
     * @param restaurant the restaurant entity
     * @return a list of Rating entities associated with the restaurant
     */
    List<Rating> findByRestaurant(Restaurant restaurant);
}