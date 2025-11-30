package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.UserDTO;
import com.gameart.backend.entity.User;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GlobalMapper mapper;

    public UserService(UserRepository userRepository, GlobalMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toSecureUserDto) 
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(String id) {
        return userRepository.findById(id)
                .map(mapper::toSecureUserDto);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toSecureUserDto);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = mapper.toUserEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapper.toSecureUserDto(savedUser);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<UserDTO> addToWishlist(String userId, String gameId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getWishlist().contains(gameId)) {
                user.getWishlist().add(gameId);
                User savedUser = userRepository.save(user);
                return Optional.of(mapper.toSecureUserDto(savedUser));
            }
            return Optional.of(mapper.toSecureUserDto(user));
        }
        return Optional.empty();
    }

    public Optional<UserDTO> removeFromWishlist(String userId, String gameId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getWishlist().remove(gameId);
            User savedUser = userRepository.save(user);
            return Optional.of(mapper.toSecureUserDto(savedUser));
        }
        return Optional.empty();
    }
}