package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Driver;
import com.weride.weride.repository.DriverRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
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
        return driverRepository.save(driver);
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
                    Driver savedDriver = driverRepository.save(driver);
                    return ResponseEntity.ok().body(savedDriver);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
        return driverRepository.findById(id)
                .map(driver -> {
                    driverRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
