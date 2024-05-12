package com.userservice.exception;

import com.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //used to create a Global Exception handler class in your project
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //whenever this kind of exception occurred in your project this method will get invoked
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
