package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping ("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
        public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
            userService.createUser(userDto);
        return ResponseEntity.ok("User created successfully");
    }
    

}
