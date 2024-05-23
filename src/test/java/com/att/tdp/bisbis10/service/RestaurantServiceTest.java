package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDTO.AddRestaurantDTO;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRestaurants() {
        when(restaurantRepository.findAll()).thenReturn(Collections.emptyList());

        restaurantService.getAllRestaurants();

        verify(restaurantRepository, times(1)).findAll();
    }

    @Test
    public void testGetRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(java.util.Optional.of(restaurant));

        restaurantService.getRestaurant(1L);

        verify(restaurantRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetRestaurantsByCuisine() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setCuisines(Collections.singletonList("Italian"));
        when(restaurantRepository.findRestaurantsByCuisinesContains(anyString())).thenReturn(Collections.singletonList(restaurant));

        restaurantService.getRestaurantsByCuisine("Italian");

        verify(restaurantRepository, times(1)).findRestaurantsByCuisinesContains(anyString());
    }

    @Test
    public void testAddRestaurant() {
        AddRestaurantDTO addRestaurantDTO = new AddRestaurantDTO();
        addRestaurantDTO.setName("Test Restaurant");
        addRestaurantDTO.setIsKosher(true);
        addRestaurantDTO.setCuisines(Collections.singletonList("Italian"));

        restaurantService.addRestaurant(addRestaurantDTO);

        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(java.util.Optional.of(restaurant));

        List<String> cuisines = Collections.singletonList("Italian");
        restaurantService.updateRestaurant(1L, cuisines);

        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testDeleteRestaurant() {
        when(restaurantRepository.existsById(anyLong())).thenReturn(true);

        restaurantService.deleteRestaurant(1L);

        verify(restaurantRepository, times(1)).deleteById(anyLong());
    }
}