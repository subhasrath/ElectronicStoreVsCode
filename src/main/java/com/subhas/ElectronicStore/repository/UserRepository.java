package com.subhas.ElectronicStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findByNameContaining(String name);

}
