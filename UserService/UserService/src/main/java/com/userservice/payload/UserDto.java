package com.userservice.payload;

import com.userservice.entity.Rating;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String about;
    private List<Rating> rating;
}
