package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weride.weride.model.Pay;

@Repository
public interface PayRepository extends JpaRepository<Pay, Integer> {
}
