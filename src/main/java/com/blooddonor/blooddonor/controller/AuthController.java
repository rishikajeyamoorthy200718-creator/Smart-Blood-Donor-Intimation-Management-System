package com.blooddonor.blooddonor.controller;

import com.blooddonor.blooddonor.entity.LoginRequest;
import com.blooddonor.blooddonor.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        boolean success =
                adminService.login(
                        request.getUsername(),
                        request.getPassword());

        if(success) {
            return "Login Successful";
        }

        return "Invalid Username or Password";
    }
}