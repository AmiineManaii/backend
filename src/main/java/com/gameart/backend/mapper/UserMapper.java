package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.UserAuthDTO;
import com.gameart.backend.dto.UserDTO;
import com.gameart.backend.entity.User;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword()); 
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setAdresse(user.getAdresse());
        dto.setWishlist(user.getWishlist());
        
        return dto;
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setAdresse(dto.getAdresse());
        user.setWishlist(dto.getWishlist());
        
        return user;
    }

    public UserAuthDTO toAuthDto(User user) {
        if (user == null) {
            return null;
        }

        UserAuthDTO authDto = new UserAuthDTO();
        authDto.setEmail(user.getEmail());
        authDto.setPassword(user.getPassword());
        
        return authDto;
    }

    public User fromAuthDto(UserAuthDTO authDto) {
        if (authDto == null) {
            return null;
        }

        User user = new User();
        user.setEmail(authDto.getEmail());
        user.setPassword(authDto.getPassword());
        
        return user;
    }

     public UserDTO toSecureDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setAdresse(user.getAdresse());
        dto.setWishlist(user.getWishlist());
        
        
        return dto;
    }
}