package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishDTO.AddDishDTO;
import com.att.tdp.bisbis10.dto.DishDTO.UpdateDishDTO;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishControllerTest {

    @InjectMocks
    private DishController dishController;

    @Mock
    private DishService dishService;

    @Test
    public void testAddDish() {
        Long restaurantId = 1L;
        AddDishDTO addDishDTO = new AddDishDTO();
        addDishDTO.setName("Test Dish");

        doNothing().when(dishService).addDish(restaurantId, addDishDTO);

        ResponseEntity<Void> response = dishController.addDish(restaurantId, addDishDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(dishService, times(1)).addDish(restaurantId, addDishDTO);
    }

    @Test
    public void testUpdateDish() {
        Long restaurantId = 1L;
        Long dishId = 1L;
        UpdateDishDTO updateDishDTO = new UpdateDishDTO();

        doNothing().when(dishService).updateDish(restaurantId, dishId, updateDishDTO);

        ResponseEntity<Void> response = dishController.updateDish(restaurantId, dishId, updateDishDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dishService, times(1)).updateDish(restaurantId, dishId, updateDishDTO);
    }

    @Test
    public void testDeleteDish() {
        Long restaurantId = 1L;
        Long dishId = 1L;

        doNothing().when(dishService).deleteDish(restaurantId, dishId);

        ResponseEntity<Void> response = dishController.deleteDish(restaurantId, dishId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(dishService, times(1)).deleteDish(restaurantId, dishId);
    }

    @Test
    public void testGetDishesByRestaurant() {
        Long restaurantId = 1L;
        List<Dish> dishes = Collections.singletonList(new Dish());

        when(dishService.getDishesByRestaurant(restaurantId)).thenReturn(dishes);

        ResponseEntity<List<Dish>> response = dishController.getDishesByRestaurant(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dishes, response.getBody());

        verify(dishService, times(1)).getDishesByRestaurant(restaurantId);
    }
}