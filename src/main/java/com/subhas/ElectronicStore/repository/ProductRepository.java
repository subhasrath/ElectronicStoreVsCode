package com.subhas.ElectronicStore.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.Category;
import com.subhas.ElectronicStore.entity.Product;
import java.util.List;



public interface ProductRepository extends JpaRepository<Product, String>{
    Page<Product> findByProductNameContaining(String title, Pageable pageable);

    Page<Product> findByLive(boolean live, Pageable pageable);

    Page<Product> findByStock(boolean stock, Pageable pageable);

    Page<Product> findByCategory(Category category);
    
}
