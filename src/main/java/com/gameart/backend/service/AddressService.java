package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gameart.backend.entity.Address;
import com.gameart.backend.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(String id) {
        return addressRepository.findById(id);
    }

    public List<Address> findByUserId(String userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void deleteById(String id) {
        addressRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return addressRepository.existsById(id);
    }

    public Optional<Address> setAsDefault(String addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            
            // Retirer le statut par défaut de toutes les adresses de cet utilisateur
            List<Address> userAddresses = addressRepository.findByUserId(address.getUserId());
            for (Address addr : userAddresses) {
                if (addr.getIsDefault() != null && addr.getIsDefault()) {
                    addr.setIsDefault(false);
                    addressRepository.save(addr);
                }
            }
            
            // Définir cette adresse comme par défaut
            address.setIsDefault(true);
            return Optional.of(addressRepository.save(address));
        }
        return Optional.empty();
    }
}