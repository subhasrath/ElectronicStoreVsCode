package com.subhas.ElectronicStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration 
public class SecurityConfig {

    @Bean 
    public UserDetailsService userDetailsService(){
        UserDetails normal = User.builder()
                    .username("subhas")
                    .password("subhas")
                    .roles("NORMAL")
                    .build();

        UserDetails admin = User.builder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build();

        return new InMemoryUserDetailsManager(normal, admin);          
    }
}
