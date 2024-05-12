package com.userservice.service;

import com.userservice.payload.UserDto;

import java.util.List;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    UserDto getUserById(String userId);
}
