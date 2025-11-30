package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.AddressDTO;
import com.gameart.backend.entity.Address;

@Component
public class AddressMapper {

    public AddressDTO toDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setNom(address.getNom());
        dto.setRue(address.getRue());
        dto.setVille(address.getVille());
        dto.setCodePostal(address.getCodePostal());
        dto.setPays(address.getPays());
        dto.setUser(address.getUser());
        dto.setIsDefault(address.getIsDefault());
        
        return dto;
    }

    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        Address address = new Address();
        address.setId(dto.getId());
        address.setNom(dto.getNom());
        address.setRue(dto.getRue());
        address.setVille(dto.getVille());
        address.setCodePostal(dto.getCodePostal());
        address.setPays(dto.getPays());
        address.setUser(dto.getUser());
        address.setIsDefault(dto.getIsDefault());
        
        return address;
    }

  }