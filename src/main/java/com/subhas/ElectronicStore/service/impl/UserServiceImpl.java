package com.subhas.ElectronicStore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.entity.User;
import com.subhas.ElectronicStore.repository.UserRepository;
import com.subhas.ElectronicStore.service.UserService;

@Service 
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // generate unique userId in String format
        userDto.setUserId(java.util.UUID.randomUUID().toString());

        // dto -> entity
        User user = dtoToEntity(userDto);
        
        User savedUser = userRepository.save(user);
        // entity -> dto
        UserDto savedUserDto = entityToDto(savedUser);
        
        return savedUserDto;
       
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with Given ID"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        User updatedUser = userRepository.save(user);
        return entityToDto(updatedUser);
    
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with Given ID"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with Given ID"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public UserDto getUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchUser'");
    }

    private UserDto entityToDto(User savedUser) {
        return UserDto.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .gender(savedUser.getGender())
                .about(savedUser.getAbout())
                .imageName(savedUser.getImageName())
                .build();
    }

    private User dtoToEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .about(userDto.getAbout())
                .imageName(userDto.getImageName())
                .build();
    }

    

}