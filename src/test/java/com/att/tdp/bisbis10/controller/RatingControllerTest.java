package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.service.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    @Test
    public void testAddRating() {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRestaurantId(1L);
        ratingDTO.setRating(4.5);

        doNothing().when(ratingService).addRating(any(RatingDTO.class));

        ResponseEntity<Void> response = ratingController.addRating(ratingDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(ratingService, times(1)).addRating(any(RatingDTO.class));
    }
}