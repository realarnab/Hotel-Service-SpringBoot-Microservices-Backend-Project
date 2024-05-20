package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.payload.UserDto;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;


//http://localhost:8082/users
    @PostMapping
    public ResponseEntity<UserDto> createUserRegistration(@RequestBody UserDto userDto){
        UserDto dto = userService.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //int retryCount=1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback") //implementing circuit breaker
    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback") //implementing retry module
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
//        System.out.println("Retry count: "+retryCount);
//        retryCount++;
        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    //Creating fallback method for circuit breaker
    public ResponseEntity<UserDto> ratingHotelFallback(String userId,Exception ex){
        UserDto userDto=new UserDto();
        userDto.setUserId(userId);
        userDto.setName("Dummy");
        userDto.setEmail("dummy@gmail.com");
        userDto.setAbout("This user created dummy because service is down!");
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
