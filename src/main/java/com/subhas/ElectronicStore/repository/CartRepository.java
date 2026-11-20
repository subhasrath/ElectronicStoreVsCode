package com.subhas.ElectronicStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.Cart;
import com.subhas.ElectronicStore.entity.User;



public interface CartRepository extends JpaRepository<Cart, String>{
    Optional<Cart> findByUser(User user);
}
