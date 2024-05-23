package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO.AddRatingDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    public void addRating(AddRatingDTO addRatingDTO) {
        Long restaurantId = addRatingDTO.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + restaurantId));
        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setValue(addRatingDTO.getRating());
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
