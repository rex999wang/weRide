package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Driver;
import com.weride.weride.repository.DriverRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final UserService userService;
    private final DriverRepository driverRepository;

    public DriverController(UserService userService, DriverRepository driverRepository) {
        this.userService = userService;
        this.driverRepository = driverRepository;
    }

    @GetMapping("/")
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Integer id) {
        return driverRepository.findById(id)
                .map(driver -> ResponseEntity.ok().body(driver))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Driver createDriver(@RequestBody Driver driver) {
         return (Driver) userService.createUser(driver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver updatedDriver) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setName(updatedDriver.getName());
                    driver.setEmail(updatedDriver.getEmail());
                    driver.setLocation(updatedDriver.getLocation());
                    driver.updateCars(updatedDriver.getCars()); 
                    // Update other fields as needed
                    userService.createUser(driver);
                    return ResponseEntity.ok().body(driver);    
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
        return driverRepository.findById(id)
                .map(driver -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
