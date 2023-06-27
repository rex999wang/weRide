package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weride.weride.model.Rider;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}
