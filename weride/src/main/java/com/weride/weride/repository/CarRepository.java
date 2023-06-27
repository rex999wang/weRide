package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weride.weride.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
