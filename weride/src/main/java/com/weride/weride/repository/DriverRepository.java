package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weride.weride.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}