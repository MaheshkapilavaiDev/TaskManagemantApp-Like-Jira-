package com.taskmanagementsystem.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.taskmanagementsystem.dto.UserRequestDTO;
import com.taskmanagementsystem.entity.User;
import com.taskmanagementsystem.enums.Role;
import com.taskmanagementsystem.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AdminController(UserRepository repository,
                           PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    // ONLY ADMIN CAN CREATE ANOTHER ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public String createAdmin(@RequestBody UserRequestDTO dto) {

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        // SET ROLE AS ADMIN
        user.setRole(Role.ADMIN);

        repository.save(user);

        return "Admin created successfully";
    }
}