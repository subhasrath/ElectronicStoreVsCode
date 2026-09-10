package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping ("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
        public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
            userService.createUser(userDto);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getBy/{userId}")
    public ResponseEntity<UserDto> getUser(@RequestParam String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUserByName(@PathVariable String keywords) {
        return ResponseEntity.ok(userService.searchUser(keywords));
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<UserDto> searchUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    
    @DeleteMapping ("/delete/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponseMessage.builder()
        .message("User deleted successfully !!")
        .success(true)
        .status(HttpStatus.OK)
        .build());
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

}
