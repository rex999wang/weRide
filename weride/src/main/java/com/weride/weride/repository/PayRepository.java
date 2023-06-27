package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weride.weride.model.Pay;

public interface PayRepository extends JpaRepository<Pay, Integer> {
}
