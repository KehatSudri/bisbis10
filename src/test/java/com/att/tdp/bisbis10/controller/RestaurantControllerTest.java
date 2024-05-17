package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    @Test
    public void testGetRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAverageRating(4.5);
        restaurant.setKosher(true);
        restaurant.setCuisines(Collections.singletonList("Italian"));

        when(restaurantService.getRestaurant(anyLong())).thenReturn(restaurant);

        ResponseEntity<Restaurant> response = restaurantController.getRestaurant(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurant, response.getBody());
        verify(restaurantService, times(1)).getRestaurant(anyLong());
    }

    @Test
    public void testGetRestaurantsByCuisine() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAverageRating(4.5);
        restaurant.setKosher(true);
        restaurant.setCuisines(Collections.singletonList("Italian"));

        when(restaurantService.getRestaurantsByCuisine(anyString())).thenReturn(Collections.singletonList(restaurant));
        when(restaurantService.getAllRestaurants()).thenReturn(Collections.singletonList(restaurant));

        ResponseEntity<List<Restaurant>> response = restaurantController.getRestaurantsByCuisine("Italian");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        verify(restaurantService, times(1)).getRestaurantsByCuisine(anyString());

        response = restaurantController.getRestaurantsByCuisine(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAverageRating(4.5);
        restaurant.setKosher(true);
        restaurant.setCuisines(Collections.singletonList("Italian"));

        doNothing().when(restaurantService).addRestaurant(any(Restaurant.class));

        ResponseEntity<Void> response = restaurantController.addRestaurant(restaurant);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(restaurantService, times(1)).addRestaurant(any(Restaurant.class));
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAverageRating(4.5);
        restaurant.setKosher(true);
        restaurant.setCuisines(Collections.singletonList("Italian"));

        doNothing().when(restaurantService).updateRestaurant(anyLong(), any(Restaurant.class));

        ResponseEntity<Void> response = restaurantController.updateRestaurant(1L, restaurant);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restaurantService, times(1)).updateRestaurant(anyLong(), any(Restaurant.class));
    }

    @Test
    public void testDeleteRestaurant() {
        doNothing().when(restaurantService).deleteRestaurant(anyLong());

        ResponseEntity<Void> response = restaurantController.deleteRestaurant(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(restaurantService, times(1)).deleteRestaurant(anyLong());
    }
}