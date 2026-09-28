package com.subhas.ElectronicStore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor 
public class CreateOrderRequest {
    private String cartId;
    private String userId;
    private String orderStatus = "PENDING";
    private String paymentStatus = "NOTPAID";
    private String billingAddress;
    private String billingPhoneNo;
    private String billingName;

}
