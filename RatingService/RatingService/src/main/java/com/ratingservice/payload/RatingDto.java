package com.ratingservice.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatingDto {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
