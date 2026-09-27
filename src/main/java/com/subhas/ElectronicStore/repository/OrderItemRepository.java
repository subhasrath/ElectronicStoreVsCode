package com.subhas.ElectronicStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
    
}
