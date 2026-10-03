package com.blooddonor.blooddonor.repository;

import com.blooddonor.blooddonor.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository
        extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
}