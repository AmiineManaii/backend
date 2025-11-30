package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.AddressDTO;
import com.gameart.backend.entity.Address;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final GlobalMapper mapper;

    public AddressService(AddressRepository addressRepository, GlobalMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(mapper::toAddressDto)
                .collect(Collectors.toList());
    }

    public Optional<AddressDTO> findById(String id) {
        return addressRepository.findById(id)
                .map(mapper::toAddressDto);
    }

    public List<AddressDTO> findByUserId(String userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(mapper::toAddressDto)
                .collect(Collectors.toList());
    }

    public AddressDTO save(AddressDTO addressDTO) {
        Address address = mapper.toAddressEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);
        return mapper.toAddressDto(savedAddress);
    }

    public void deleteById(String id) {
        addressRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return addressRepository.existsById(id);
    }

    public Optional<AddressDTO> setAsDefault(String addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            
          
            List<Address> userAddresses = addressRepository.findByUserId(address.getUserId());
            for (Address addr : userAddresses) {
                if (addr.getIsDefault() != null && addr.getIsDefault()) {
                    addr.setIsDefault(false);
                    addressRepository.save(addr);
                }
            }
            
            
            address.setIsDefault(true);
            Address savedAddress = addressRepository.save(address);
            return Optional.of(mapper.toAddressDto(savedAddress));
        }
        return Optional.empty();
    }
}