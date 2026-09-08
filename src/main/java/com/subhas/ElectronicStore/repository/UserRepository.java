package com.subhas.ElectronicStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
