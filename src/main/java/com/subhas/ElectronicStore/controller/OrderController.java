package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.CreateOrderRequest;
import com.subhas.ElectronicStore.dto.OrderDto;
import com.subhas.ElectronicStore.dto.ProductDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.payload.PageableResponse;
import com.subhas.ElectronicStore.service.OrderService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping ("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderDto order = orderService.createOrder(request);
        
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<OrderDto>> getAllOrders(
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber, 
        @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ) {
        PageableResponse<OrderDto> response = orderService.getOrders(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrderForUser(@RequestParam String userId) {
        List<OrderDto> response = orderService.getOrdersOfUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping 
    public ResponseEntity<ApiResponseMessage> removeOrder(@RequestParam String orderId){
        orderService.removeOrder(orderId);
        ApiResponseMessage response = ApiResponseMessage.builder().message("Order removed succesfully").success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    

}
