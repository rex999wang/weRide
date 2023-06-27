package com.weride.weride.controller;

import org.springframework.web.bind.annotation.*;

import com.weride.weride.model.Car;
import com.weride.weride.repository.CarRepository;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        return carRepository.findById(id)
                .map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setDriver(updatedCar.getDriver());
                    car.setMake(updatedCar.getMake());
                    car.setType(updatedCar.getType());
                    car.setColor(updatedCar.getColor());
                    car.setPlateNo(updatedCar.getPlateNo());
                    // Update other fields as needed
                    Car savedCar = carRepository.save(car);
                    return ResponseEntity.ok().body(savedCar);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        return carRepository.findById(id)
                .map(car -> {
                    carRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
