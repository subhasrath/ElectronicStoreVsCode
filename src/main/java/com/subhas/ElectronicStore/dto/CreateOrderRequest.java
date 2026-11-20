package com.subhas.ElectronicStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateOrderRequest {
    @NotBlank (message = "Cart id is required")
    private String cartId;
    @NotBlank (message = "User id is required")
    private String userId;
    
    private String orderStatus = "PENDING";
    
    private String paymentStatus = "NOTPAID";
    @NotBlank (message = "Billing address is required")
    private String billingAddress;
    @NotBlank (message = "Phone no is required")
    private String billingPhoneNo;
    @NotBlank (message = "billing name is required")
    private String billingName;

}
