package com.subhas.ElectronicStore.service;

import java.util.List;


import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.payload.PageableResponse;


public interface UserService {
    UserDto createUser(UserDto user);
    
    UserDto updateUser(UserDto user, String userId);
    
    void deleteUser(String userId);
    
    UserDto getUserById(String userId);
    
    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);

    


    
}
