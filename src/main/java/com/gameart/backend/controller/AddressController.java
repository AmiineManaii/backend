package com.gameart.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameart.backend.entity.Address;
import com.gameart.backend.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/addresses")
@Tag(name = "Addresses", description = "API de gestion des adresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @Operation(summary = "Obtenir toutes les adresses")
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une adresse par son ID")
    public ResponseEntity<Address> getAddressById(@PathVariable String id) {
        Optional<Address> address = addressService.findById(id);
        return address.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir les adresses d'un utilisateur")
    public List<Address> getAddressesByUserId(@PathVariable String userId) {
        return addressService.findByUserId(userId);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle adresse")
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une adresse")
    public ResponseEntity<Address> updateAddress(@PathVariable String id, @RequestBody Address addressDetails) {
        Optional<Address> optionalAddress = addressService.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setNom(addressDetails.getNom());
            address.setRue(addressDetails.getRue());
            address.setVille(addressDetails.getVille());
            address.setCodePostal(addressDetails.getCodePostal());
            address.setPays(addressDetails.getPays());
            address.setUserId(addressDetails.getUserId());
            address.setIsDefault(addressDetails.getIsDefault());
            return ResponseEntity.ok(addressService.save(address));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une adresse")
    public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
        if (addressService.existsById(id)) {
            addressService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/default")
    @Operation(summary = "Définir une adresse comme par défaut")
    public ResponseEntity<Address> setDefaultAddress(@PathVariable String id) {
        Optional<Address> address = addressService.setAsDefault(id);
        return address.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}