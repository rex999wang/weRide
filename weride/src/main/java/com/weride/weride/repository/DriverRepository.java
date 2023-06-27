package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weride.weride.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}