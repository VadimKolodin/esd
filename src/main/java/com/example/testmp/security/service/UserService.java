package com.example.testmp.security.service;

import com.example.testmp.exception.NotFoundException;
import com.example.testmp.security.model.Authority;
import com.example.testmp.user.data.UserEntity;
import com.example.testmp.user.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity getUserByLogin(String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }

    public UserEntity getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<UserEntity> getUsersByRole(Authority role) {
        return userRepository.findAllByRole(role);
    }


    public void createNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Authority.CLIENT);
        userRepository.save(user);
    }

}