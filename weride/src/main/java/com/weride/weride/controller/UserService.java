package com.weride.weride.controller;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.weride.weride.model.Admin;
import com.weride.weride.model.Driver;
import com.weride.weride.model.Rider;
import com.weride.weride.model.User;
import com.weride.weride.repository.AdminRepository;
import com.weride.weride.repository.DriverRepository;
import com.weride.weride.repository.RiderRepository;
import com.weride.weride.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, AdminRepository adminRepository,
    DriverRepository driverRepository, RiderRepository riderRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user instanceof Admin) {
            return adminRepository.save((Admin) user);
        } else if (user instanceof Driver) {
            return driverRepository.save((Driver) user);
        } else if (user instanceof Rider) {
            return riderRepository.save((Rider) user);
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        if (user instanceof Admin) {
            adminRepository.deleteById(id);
        } else if (user instanceof Driver) {
            driverRepository.deleteById(id);
        } else if (user instanceof Rider) {
            riderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
