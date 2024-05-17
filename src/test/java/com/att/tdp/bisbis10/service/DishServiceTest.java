package com.att.tdp.bisbis10.service;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.BadRequestException;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @InjectMocks
    private DishService dishService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private Dish dish;

    @BeforeEach
    public void setUp() {
        dish = new Dish();
        dish.setId(1L);
        dish.setName("Test Dish");
        dish.setDescription("Test Description");
        dish.setPrice(10.0);
        dish.setRestaurant(new Restaurant()); // Set properties for Restaurant if needed
    }

    @Test
    public void testAddDish() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        dishService.addDish(1L, dish);

        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishRepository, times(1)).save(dishCaptor.capture());
        Dish savedDish = dishCaptor.getValue();
        assertEquals(dish, savedDish);
        assertEquals(restaurant, savedDish.getRestaurant());
    }

    @Test
    public void testUpdateDish() {
        Restaurant restaurant = new Restaurant();
        Dish existingDish = new Dish();
        existingDish.setRestaurant(restaurant);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(existingDish));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        dishService.updateDish(1L, 1L, dish);

        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishRepository, times(1)).save(dishCaptor.capture());
        Dish updatedDish = dishCaptor.getValue();
        assertEquals(dish.getDescription(), updatedDish.getDescription());
        assertEquals(dish.getPrice(), updatedDish.getPrice());
    }

    @Test
    public void testDeleteDish() {
        Restaurant restaurant = new Restaurant();
        Dish existingDish = new Dish();
        existingDish.setRestaurant(restaurant);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(existingDish));

        dishService.deleteDish(1L, 1L);

        verify(dishRepository, times(1)).delete(existingDish);
    }

    @Test
    public void testGetDishesByRestaurant() {
        List<Dish> dishes = Collections.singletonList(dish);
        when(dishRepository.findByRestaurantId(anyLong())).thenReturn(dishes);

        List<Dish> result = dishService.getDishesByRestaurant(1L);

        assertEquals(dishes, result);
        verify(dishRepository, times(1)).findByRestaurantId(anyLong());
    }

    @Test
    public void testAddDishThrowsResourceNotFoundException() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dishService.addDish(1L, dish));
    }

    @Test
    public void testUpdateDishThrowsResourceNotFoundExceptionForRestaurant() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dishService.updateDish(1L, 1L, dish));
    }

    @Test
    public void testUpdateDishThrowsResourceNotFoundExceptionForDish() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dishService.updateDish(1L, 1L, dish));
    }

    @Test
    public void testUpdateDishThrowsBadRequestException() {
        Restaurant restaurant = new Restaurant();
        Restaurant anotherRestaurant = new Restaurant();
        Dish existingDish = new Dish();
        existingDish.setRestaurant(anotherRestaurant);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(existingDish));

        assertThrows(BadRequestException.class, () -> dishService.updateDish(1L, 1L, dish));
    }

    @Test
    public void testDeleteDishThrowsResourceNotFoundExceptionForRestaurant() {
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dishService.deleteDish(1L, 1L));
    }

    @Test
    public void testDeleteDishThrowsResourceNotFoundExceptionForDish() {
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dishService.deleteDish(1L, 1L));
    }

    @Test
    public void testDeleteDishThrowsBadRequestException() {
        Restaurant restaurant = new Restaurant();
        Restaurant anotherRestaurant = new Restaurant();
        Dish existingDish = new Dish();
        existingDish.setRestaurant(anotherRestaurant);
        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(existingDish));

        assertThrows(BadRequestException.class, () -> dishService.deleteDish(1L, 1L));
    }
}