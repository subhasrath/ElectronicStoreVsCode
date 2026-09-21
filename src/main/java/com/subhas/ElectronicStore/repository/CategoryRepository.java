package com.subhas.ElectronicStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subhas.ElectronicStore.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, String>{

    Page<Category> findByTitleContaining(String title, Pageable pageable);

}
