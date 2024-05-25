package com.ratingservice.controller;

import com.ratingservice.payload.RatingDto;
import com.ratingservice.service.RatingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto){
        RatingDto dto = ratingService.create(ratingDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings(){
        List<RatingDto> ratings = ratingService.getRatings();
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingsByUser(@PathVariable String userId){
        List<RatingDto> ratingByUserId = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratingByUserId,HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RatingDto>> getRatingsByHotel(@PathVariable String hotelId){
        List<RatingDto> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratingByHotelId,HttpStatus.OK);
    }
}
