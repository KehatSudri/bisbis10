package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO.AddRatingDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is a service class for the Rating entity
 * It uses RatingRepository and RestaurantRepository to provide service operations for the Rating entity
 * It includes methods to add ratings
 */
@RequiredArgsConstructor
@Service
public class RatingService {

    /**
     * Repository for managing ratings in the database
     */
    private final RatingRepository ratingRepository;

    /**
     * Repository for managing restaurants in the database
     */
    private final RestaurantRepository restaurantRepository;

    /**
     * Adds a new rating for a specific restaurant
     * @param addRatingDTO the data transfer object containing the details of the rating to be added
     */
    public void addRating(AddRatingDTO addRatingDTO) {
        Long restaurantId = addRatingDTO.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));
        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setValue(addRatingDTO.getRating());
        ratingRepository.save(rating);

        List<Rating> ratings = ratingRepository.findByRestaurant(restaurant);
        // Calculate the average rating
        double averageRating = ratings.stream()
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0.0);

        restaurant.setAverageRating(averageRating);
        restaurantRepository.save(restaurant);
    }
}