package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDTO.AddDishDTO;
import com.att.tdp.bisbis10.dto.DishDTO.UpdateDishDTO;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DishServiceTest {

    @InjectMocks
    private DishService dishService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDish() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));

        AddDishDTO addDishDTO = new AddDishDTO();
        addDishDTO.setName("Pizza");
        addDishDTO.setDescription("Delicious cheese pizza");
        addDishDTO.setPrice(10.0);

        dishService.addDish(1L, addDishDTO);

        verify(dishRepository, times(1)).save(any(Dish.class));
    }

    @Test
    public void testUpdateDish() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));

        Dish dish = new Dish();
        dish.setRestaurant(restaurant);
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));

        UpdateDishDTO updateDishDTO = new UpdateDishDTO();
        updateDishDTO.setDescription("Delicious cheese pizza");
        updateDishDTO.setPrice(10.0);

        dishService.updateDish(1L, 1L, updateDishDTO);

        verify(dishRepository, times(1)).save(any(Dish.class));
    }

    @Test
    public void testDeleteDish() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));

        Dish dish = new Dish();
        dish.setRestaurant(restaurant);
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));

        dishService.deleteDish(1L, 1L);

        verify(dishRepository, times(1)).delete(any(Dish.class));
    }

    @Test
    public void testGetDishesByRestaurant() {
        Dish dish = new Dish();
        dish.setName("Pizza");
        dish.setDescription("Delicious cheese pizza");
        dish.setPrice(10.0);
        when(dishRepository.findByRestaurantId(anyLong())).thenReturn(Collections.singletonList(dish));

        dishService.getDishesByRestaurant(1L);

        verify(dishRepository, times(1)).findByRestaurantId(anyLong());
    }
}