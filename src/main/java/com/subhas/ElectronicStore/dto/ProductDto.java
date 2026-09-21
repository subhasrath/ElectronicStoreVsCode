package com.subhas.ElectronicStore.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class ProductDto {
    private String productId;
    @NotBlank 
    @Size (min = 2, message = "Product Name should be minimunm of two characters")
    private String productName;
    @Size (min = 2, max = 10000, message = "description should be minimum 2 characters and maximum of 10000 characters")
    private String description;
    private int price;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
}
