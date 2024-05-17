package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addRating(RatingDTO ratingDTO) {
        Long restaurantId = ratingDTO.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));
        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setValue(ratingDTO.getRating());
        ratingRepository.save(rating);

        List<Rating> ratings = ratingRepository.findByRestaurant(restaurant);
        double averageRating = ratings.stream()
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0.0);

        restaurant.setAverageRating(averageRating);
        restaurantRepository.save(restaurant);
    }
}
