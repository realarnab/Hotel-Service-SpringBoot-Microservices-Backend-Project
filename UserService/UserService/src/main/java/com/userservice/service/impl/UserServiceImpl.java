package com.userservice.service.impl;

import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.external.services.HotelService;
import com.userservice.payload.UserDto;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;
    @Autowired
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HotelService hotelService;
    @Override
    public UserDto createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString(); //to generate the random user id
        userDto.setUserId(userId); //set the user id
        User user = mapToEntity(userDto);
        User saved = userRepository.save(user);
        UserDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> all = userRepository.findAll();
        List<UserDto> collect = all.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Resource not found!!"));
        //fetch ratings of the user
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/api/ratings/user/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser); //information about the ratings of the user

        //List<Rating> ratingsByUser = Arrays.stream(ratingsOfUser).toList();
        List <Rating> ratingsByUser=Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratings = ratingsByUser.stream().map(rating -> {
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
           // logger.info("Response Status Code: ", forEntity.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratings); //set the ratings of this user
        UserDto dto = mapToDto(user);
        return dto;
    }

    public UserDto mapToDto(User user){
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

    public User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return  user;
    }
}
