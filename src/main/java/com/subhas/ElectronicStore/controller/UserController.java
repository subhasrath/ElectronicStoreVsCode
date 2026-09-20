package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.payload.ImageResponse;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.FileService;
import com.subhas.ElectronicStore.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
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
    private final FileService fileService;

    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }
    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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
    public ResponseEntity<PageableResponse<UserDto>> getUser(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "name",required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir) {
        return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir));
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
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) throws IOException {
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

    // upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable String userId) throws IOException{
        
        String imageName = fileService.uploadImage(image, imageUploadPath);
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        userService.updateUser(user, userId);
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }
    // serve User Image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response)throws IOException{
        UserDto user = userService.getUserById(userId);
        logger.info("user image name: {}", user.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
