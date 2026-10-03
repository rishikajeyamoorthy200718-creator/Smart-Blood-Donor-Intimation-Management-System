package com.blooddonor.blooddonor.service;

import com.blooddonor.blooddonor.entity.Admin;
import com.blooddonor.blooddonor.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String username,
                         String password) {

        Admin admin =
                adminRepository.findByUsername(username);

        if (admin == null) {
            return false;
        }

        return admin.getPassword().equals(password);
    }
}