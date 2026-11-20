package com.subhas.ElectronicStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.Order;
import com.subhas.ElectronicStore.entity.User;

public interface OrderRepository extends JpaRepository<Order, String>{
    List<Order> findByUser(User user);
}
