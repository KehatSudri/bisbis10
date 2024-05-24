package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RatingDTO.AddRatingDTO;
import com.att.tdp.bisbis10.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for managing ratings
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    /**
     * Service for managing ratings
     */
    private final RatingService ratingService;

    /**
     * Endpoint for adding a new rating
     * @param addRatingDTO The details of the rating to be added
     * @return HTTP status indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<Void> addRating(@Valid @RequestBody AddRatingDTO addRatingDTO) {
        ratingService.addRating(addRatingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}