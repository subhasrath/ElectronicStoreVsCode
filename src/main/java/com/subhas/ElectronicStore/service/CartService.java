package com.subhas.ElectronicStore.service;

import com.subhas.ElectronicStore.dto.AddItemToCartRequest;
import com.subhas.ElectronicStore.dto.CartDto;

public interface CartService {

    //add item to cart
    CartDto addItemToCart(String userId, AddItemToCartRequest addItemRequest);

    // remove item from cart
    void removeItemFromCart(String userId, int cartItem);
     
    // remove all items from cart
    void clearCart(String userId);

    CartDto getCartByUser(String userId);
}
