package com.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("user_ratings") //used in order to bind the class to the database table
public class Rating {
    @Id //in mongodb id will be auto generated
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}

