package com.subhas.ElectronicStore.config;

import com.subhas.ElectronicStore.dto.UserDto;
import com.subhas.ElectronicStore.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(UserDto.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setRoles));

        return modelMapper;
    } 
}
