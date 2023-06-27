package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weride.weride.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
