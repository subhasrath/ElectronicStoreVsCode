package com.subhas.ElectronicStore.service;

import java.io.IOException;
import java.util.List;


import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.payload.PageableResponse;


public interface UserService {
    UserDto createUser(UserDto user);
    
    UserDto updateUser(UserDto user, String userId);
    
    void deleteUser(String userId) throws IOException;
    
    UserDto getUserById(String userId);
    
    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);

    


    
}
