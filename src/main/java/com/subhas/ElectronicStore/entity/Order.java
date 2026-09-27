package com.subhas.ElectronicStore.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
@Table (name = "orders")
public class Order {
    @Id 
    private String orderId;
    // Pending, Dispatched, Delivered
    // can use ENUM
    private String orderStatus;
    // Not-Paid, Paid
    // boolean , enum can be used
    private String paymentStatus;

    private int orderAmount;

    private String billingAddress;

    private String billingPhoneNo;

    private String billingName;

    private Date orderedDate;

    private Date deliveryDate;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;

    @OneToMany (mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

}
