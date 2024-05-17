package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRating() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRestaurantId(1L);
        ratingDTO.setRating(5.0);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setValue(5.0);

        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurant));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(ratingRepository.findByRestaurant(any(Restaurant.class))).thenReturn(List.of(rating));

        ratingService.addRating(ratingDTO);

        verify(restaurantRepository, times(1)).findById(any(Long.class));
        verify(ratingRepository, times(1)).save(any(Rating.class));
        verify(ratingRepository, times(1)).findByRestaurant(any(Restaurant.class));
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testAddRatingRestaurantNotFound() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRestaurantId(1L);
        ratingDTO.setRating(5.0);

        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        try {
            ratingService.addRating(ratingDTO);
        } catch (ResourceNotFoundException e) {
            assert(e.getMessage().contains("Restaurant not found with id"));
        }

        verify(restaurantRepository, times(1)).findById(any(Long.class));
        verify(ratingRepository, times(0)).save(any(Rating.class));
        verify(ratingRepository, times(0)).findByRestaurant(any(Restaurant.class));
        verify(restaurantRepository, times(0)).save(any(Restaurant.class));
    }
}