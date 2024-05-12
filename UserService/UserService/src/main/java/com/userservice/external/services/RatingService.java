package com.userservice.external.services;

import com.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping
    Rating createRating(@RequestBody Rating rating);
    @GetMapping
    Rating getAllRating();
    @GetMapping("/api/ratings/{ratingId}")
    Rating getRatingById(@PathVariable String ratingId);
    @DeleteMapping("/api/ratings/{ratingId}")
    void deleteRatingById(@PathVariable String ratingId);

    @PutMapping("/api/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);
}
