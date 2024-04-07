package com.gl.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.springboot.model.Role;
import com.gl.springboot.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        roleRepository.save(role);
        return ResponseEntity.ok("Role added successfully");
    }

    // Other endpoints for roles
}