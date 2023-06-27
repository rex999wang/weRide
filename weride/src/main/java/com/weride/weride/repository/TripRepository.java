package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weride.weride.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
