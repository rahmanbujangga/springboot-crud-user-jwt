package com.bujangga.springdemo.service;

import com.bujangga.springdemo.shared.dto.UserDto;
import com.bujangga.springdemo.ui.model.response.OperationalStatusModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String id);
    UserDto updateUser(String id, UserDto userDto);
    void deleteUser(String userId);
}
