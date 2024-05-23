package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RestaurantDTO.AddRestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantDTO.GetRestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantDTO.UpdateRestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        when(restaurantService.getRestaurantsByCuisine(anyString())).thenReturn(Collections.singletonList(restaurant));
        when(restaurantService.getAllRestaurants()).thenReturn(Collections.singletonList(restaurant));

        ResponseEntity<List<GetRestaurantDTO>> response = restaurantController.getRestaurantsByCuisine("Italian");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(restaurantService, times(1)).getRestaurantsByCuisine(anyString());

        response = restaurantController.getRestaurantsByCuisine(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    public void testAddRestaurant() {
        AddRestaurantDTO addRestaurantDTO = new AddRestaurantDTO();

        doNothing().when(restaurantService).addRestaurant(any(AddRestaurantDTO.class));

        ResponseEntity<Void> response = restaurantController.addRestaurant(addRestaurantDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(restaurantService, times(1)).addRestaurant(any(AddRestaurantDTO.class));
    }

    @Test
    public void testUpdateRestaurant() {
        UpdateRestaurantDTO updateRestaurantDTO = new UpdateRestaurantDTO();
        updateRestaurantDTO.setCuisines(Arrays.asList("Italian", "Mexican"));

        doNothing().when(restaurantService).updateRestaurant(anyLong(), anyList());

        ResponseEntity<Void> response = restaurantController.updateRestaurant(1L, updateRestaurantDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restaurantService, times(1)).updateRestaurant(anyLong(), anyList());
    }

    @Test
    public void testDeleteRestaurant() {
        doNothing().when(restaurantService).deleteRestaurant(anyLong());

        ResponseEntity<Void> response = restaurantController.deleteRestaurant(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(restaurantService, times(1)).deleteRestaurant(anyLong());
    }
}