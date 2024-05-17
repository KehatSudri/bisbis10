package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.entity.Restaurant;
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

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRestaurants() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findAll()).thenReturn(List.of(restaurant));
        restaurantService.getAllRestaurants();
        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testGetRestaurant() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurant));
        restaurantService.getRestaurant(1L);
        verify(restaurantRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetRestaurantsByCuisine() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findRestaurantsByCuisinesContains(any(String.class))).thenReturn(List.of(restaurant));
        restaurantService.getRestaurantsByCuisine("Italian");
        verify(restaurantRepository, times(1)).findRestaurantsByCuisinesContains(any(String.class));
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurantService.addRestaurant(restaurant);
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(any(Long.class))).thenReturn(Optional.of(restaurant));
        restaurantService.updateRestaurant(1L, restaurant);
        verify(restaurantRepository, times(1)).findById(any(Long.class));
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testDeleteRestaurant() {
        when(restaurantRepository.existsById(any(Long.class))).thenReturn(true);
        restaurantService.deleteRestaurant(1L);
        verify(restaurantRepository, times(1)).existsById(any(Long.class));
        verify(restaurantRepository, times(1)).deleteById(any(Long.class));
    }
}