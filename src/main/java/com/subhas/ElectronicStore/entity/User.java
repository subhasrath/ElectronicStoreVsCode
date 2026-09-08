package com.subhas.ElectronicStore.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Table(name="users")
public class User {
    @Id
    private String userId;
    @Column(name="user_name", nullable=false, length=100)
    private String name;
    @Column(name="user_email", nullable=false, length=100, unique=true)
    private String email;
    @Column(name="user_password", nullable=false, length=100)
    private String password;
    @Column(name="user_gender", length=10)
    private String gender;
    @Column(name="user_about", length=200)
    private String about;
    @Column(name="user_image_name", length=200)
    private String imageName;
}
