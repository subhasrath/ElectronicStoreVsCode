package com.subhas.ElectronicStore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Min(value = 4, message = "title must be of minimum 4 characters")
    private String title;
    @NotBlank (message = "Description required !!")
    private String description;
    @NotBlank 
    private String coverImage;
}
