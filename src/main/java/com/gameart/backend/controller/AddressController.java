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

import com.gameart.backend.dto.AddressDTO;
import com.gameart.backend.dto.ApiResponse;
import com.gameart.backend.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
    public ApiResponse<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.findAll();
        return ApiResponse.success("Adresses récupérées avec succès", addresses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une adresse par son ID")
    public ResponseEntity<ApiResponse<AddressDTO>> getAddressById(@PathVariable String id) {
        Optional<AddressDTO> address = addressService.findById(id);
        return address.map(a -> ResponseEntity.ok(ApiResponse.success("Adresse trouvée", a)))
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir les adresses d'un utilisateur")
    public ApiResponse<List<AddressDTO>> getAddressesByUserId(@PathVariable String userId) {
        List<AddressDTO> addresses = addressService.findByUserId(userId);
        return ApiResponse.success("Adresses utilisateur récupérées", addresses);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle adresse")
    public ApiResponse<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        AddressDTO savedAddress = addressService.save(addressDTO);
        return ApiResponse.success("Adresse créée avec succès", savedAddress);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une adresse")
    public ResponseEntity<ApiResponse<AddressDTO>> updateAddress(@PathVariable String id, @Valid @RequestBody AddressDTO addressDetails) {
        if (!addressService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        addressDetails.setId(id); 
        AddressDTO updatedAddress = addressService.save(addressDetails);
        return ResponseEntity.ok(ApiResponse.success("Adresse mise à jour avec succès", updatedAddress));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une adresse")
    public ResponseEntity<ApiResponse<Void>> deleteAddress(@PathVariable String id) {
        if (addressService.existsById(id)) {
            addressService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Adresse supprimée avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/default")
    @Operation(summary = "Définir une adresse comme par défaut")
    public ResponseEntity<ApiResponse<AddressDTO>> setDefaultAddress(@PathVariable String id) {
        Optional<AddressDTO> address = addressService.setAsDefault(id);
        return address.map(a -> ResponseEntity.ok(ApiResponse.success("Adresse définie comme par défaut", a)))
                     .orElse(ResponseEntity.notFound().build());
    }
}