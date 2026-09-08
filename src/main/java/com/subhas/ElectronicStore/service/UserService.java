package com.subhas.ElectronicStore.service;

import java.util.List;

import com.subhas.ElectronicStore.dto.UserDto;


public interface UserService {
    UserDto createUser(UserDto user);
    
    UserDto updateUser(UserDto user, String userId);
    
    void deleteUser(String userId);
    
    UserDto getUserById(String userId);
    
    List<UserDto> getAllUsers();

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);

    


    
}
