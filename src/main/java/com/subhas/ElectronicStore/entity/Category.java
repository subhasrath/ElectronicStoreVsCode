package com.subhas.ElectronicStore.entity;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany (mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
