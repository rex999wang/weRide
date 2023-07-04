package com.weride.weride.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMIN")
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    // Admin-specific fields and methods...
}
