package com.ratingservice.service;

import com.ratingservice.payload.RatingDto;

import java.util.List;

public interface RatingService {
    RatingDto create(RatingDto ratingDto);
    List<RatingDto> getRatings();
    List<RatingDto> getRatingByUserId(String userId);
    List<RatingDto> getRatingByHotelId(String hotelId);

}
