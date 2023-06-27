package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weride.weride.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
