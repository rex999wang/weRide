package com.weride.weride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weride.weride.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
