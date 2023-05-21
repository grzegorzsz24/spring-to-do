package com.example.projekt.service;

import com.example.projekt.model.Role;
import com.example.projekt.model.Task;
import com.example.projekt.model.User;
import com.example.projekt.model.UserCredentialsDto;
import com.example.projekt.repository.RoleRepository;
import com.example.projekt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public UserService(UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByRole("uzytkownik");
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return user;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Task> getTasksByUserId(Long UserId) {
        return userRepository.findById(UserId)
                .map(User::getTasks)
                .orElse(Collections.emptyList());
    }
    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }
}
