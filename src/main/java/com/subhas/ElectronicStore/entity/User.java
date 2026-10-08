package com.subhas.ElectronicStore.entity;


import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter @Setter 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Table(name="users")
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(name="user_name", nullable=false, length=100)
    private String name;
    @Column(name="user_email", nullable=false, length=100, unique=true)
    private String email;
    @Column(name="user_password", nullable=false, length=500)
    private String password;
    @Column(name="user_gender", length=10)
    private String gender;
    @Column(name="user_about", length=200)
    private String about;
    @Column(name="user_image_name", length=200)
    private String imageName;
    @OneToMany (mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        return authorities;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return  this.email;
    }

    @Override
    public String getPassword() {
        return  this.password;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
