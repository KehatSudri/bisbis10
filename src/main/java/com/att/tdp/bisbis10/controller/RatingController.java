package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RatingDTO.AddRatingDTO;
import com.att.tdp.bisbis10.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<Void> addRating(@Valid @RequestBody AddRatingDTO addRatingDTO) {
        ratingService.addRating(addRatingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}