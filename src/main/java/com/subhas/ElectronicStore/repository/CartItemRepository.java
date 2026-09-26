package com.subhas.ElectronicStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    
}
