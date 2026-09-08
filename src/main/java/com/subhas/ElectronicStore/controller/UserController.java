package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
            userService.createUser(userDto);
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/getBy{userId}")
    public ResponseEntity<UserDto> getUser(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
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
    

}
