package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Rider;
import com.weride.weride.repository.RiderRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
public class RiderController {

    private final UserService userService;
    private final RiderRepository riderRepository;

    public RiderController(UserService userService, RiderRepository riderRepository) {
        this.userService = userService;
        this.riderRepository = riderRepository;
    }

    @GetMapping("/")
    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rider> getRiderById(@PathVariable Integer id) {
        return riderRepository.findById(id)
                .map(rider -> ResponseEntity.ok().body(rider))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Rider createRider(@RequestBody Rider rider) {
        return (Rider) userService.createUser(rider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rider> updateRider(@PathVariable Integer id, @RequestBody Rider updatedRider) {
        return riderRepository.findById(id)
                .map(rider -> {
                    rider.setName(updatedRider.getName());
                    rider.setEmail(updatedRider.getEmail());
                    rider.setLocation(updatedRider.getLocation());
                    // Update other fields as needed
                    userService.createUser(rider);
                    return ResponseEntity.ok().body(rider);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRider(@PathVariable Integer id) {
        return riderRepository.findById(id)
                .map(rider -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
