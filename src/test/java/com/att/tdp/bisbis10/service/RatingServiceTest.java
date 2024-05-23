package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RatingDTO.AddRatingDTO;
import com.att.tdp.bisbis10.entity.Rating;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RatingRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRating() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(java.util.Optional.of(restaurant));

        AddRatingDTO addRatingDTO = new AddRatingDTO();
        addRatingDTO.setRestaurantId(1L);
        addRatingDTO.setRating(5.0);

        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setValue(5.0);
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        when(ratingRepository.findByRestaurant(any(Restaurant.class))).thenReturn(Collections.singletonList(rating));

        ratingService.addRating(addRatingDTO);

        verify(ratingRepository, times(1)).save(any(Rating.class));
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }
}