package com.subhas.ElectronicStore.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.Product;
import com.subhas.ElectronicStore.payload.PageableResponse;

public interface ProductRepository extends JpaRepository<Product, String>{
    PageableResponse<Product> findByTitleContaining(String subTitle, Pageable pageable);

    PageableResponse<Product> findByLive(Pageable pageable);
    
}
