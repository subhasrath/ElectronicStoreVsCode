package com.subhas.ElectronicStore.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ImageResponse
 */
@Getter @Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class ImageResponse {
    private String imageName;
    private String message;
    private boolean success;
    private HttpStatus status;
}
