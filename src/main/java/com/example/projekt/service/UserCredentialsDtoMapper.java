package com.example.projekt.service;

import com.example.projekt.model.Role;
import com.example.projekt.model.User;
import com.example.projekt.model.UserCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class UserCredentialsDtoMapper {
    static UserCredentialsDto map(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email, password, roles);
    }
}