package com.subhas.ElectronicStore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subhas.ElectronicStore.dto.AddItemToCartRequest;
import com.subhas.ElectronicStore.dto.CartDto;
import com.subhas.ElectronicStore.payload.ApiResponseMessage;
import com.subhas.ElectronicStore.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping ("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDto> addItemsToCart(@RequestBody AddItemToCartRequest addItemToCartRequest, @RequestParam String userId) {
        CartDto cart = cartService.addItemToCart(userId, addItemToCartRequest);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CartDto> getItemsFromCart(@RequestParam String userId) {
        CartDto cart = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping 
    public ResponseEntity<ApiResponseMessage> removeItemsFromCart(@RequestParam String userId, @RequestParam int itemId){
        cartService.removeItemFromCart(userId, itemId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                                    .message("Item is removed")
                                    .status(HttpStatus.OK)
                                    .success(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clearAll") 
    public ResponseEntity<ApiResponseMessage> clearCart(@RequestParam String userId){
        cartService.clearCart(userId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                                    .message("Cart is Cleared")
                                    .status(HttpStatus.OK)
                                    .success(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
}
