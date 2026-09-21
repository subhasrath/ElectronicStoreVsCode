package com.subhas.ElectronicStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@Getter @Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Category {
    @Id
    @Column(name = "id")
    private String categoryId;
    
    @Column (name = "category_title", length = 60, nullable = false)
    private String title;
    
    @Column (name = "category_desc", length = 70)
    private String description;
    private String coverImage;
}
