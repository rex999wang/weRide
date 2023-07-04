package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Admin;
import com.weride.weride.repository.AdminRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/Admins")
public class AdminController {

    private final UserService userService;
    private final AdminRepository AdminRepository;

    public AdminController(UserService userService, AdminRepository AdminRepository) {
        this.userService = userService;
        this.AdminRepository = AdminRepository;
    }

    @GetMapping("/")
    public List<Admin> getAllAdmins() {
        return AdminRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        return AdminRepository.findById(id)
                .map(Admin -> ResponseEntity.ok().body(Admin))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Admin createAdmin(@RequestBody Admin Admin) {
        return (Admin) userService.createUser(Admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody Admin updatedAdmin) {
        return AdminRepository.findById(id)
                .map(Admin -> {
                    Admin.setName(updatedAdmin.getName());
                    Admin.setEmail(updatedAdmin.getEmail());
                    // Update other fields as needed
                    userService.createUser(Admin);
                    return ResponseEntity.ok().body(Admin);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
        return AdminRepository.findById(id)
                .map(Admin -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
