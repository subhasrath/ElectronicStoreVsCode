package com.subhas.ElectronicStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
@Builder 
public class UserDto {

    private String userId;
    
    @Size(min = 3, max= 15, message = "Invalid Name!!")
    private String name;
    
    @Email(message = "Invalid Email!!!")
    @Pattern (
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
        message = "Please provide a valid email address"
        )
    private String email;
    
    @NotBlank (message="password can not be blank")
    // @Size(min=8, max=32, message = "password must be minimum of 8 characters and maximum 32 characters!!!")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
        message = "Password must be 8-20 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character (@#$%^&+=), and no spaces."
        )
    private String password;

    @Size(min=4, max=10, message = "Invalid Gender")
    private String gender;

    @NotBlank (message = "about can not be empty!!")
    private String about;
    
    private String imageName;
}
