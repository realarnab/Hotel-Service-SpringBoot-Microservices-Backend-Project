package com.userservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String userid;
    private String hotelId;
    private int rating;
    private String feedback;

    private Hotel hotel;
}
