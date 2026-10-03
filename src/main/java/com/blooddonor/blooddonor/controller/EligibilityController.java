package com.blooddonor.blooddonor.controller;

import com.blooddonor.blooddonor.entity.EligibilityResponse;
import com.blooddonor.blooddonor.service.DonorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    private final DonorService donorService;

    public EligibilityController(
            DonorService donorService)
    {
        this.donorService = donorService;
    }

    @GetMapping("/{id}")
    public EligibilityResponse
    getEligibility(
            @PathVariable Long id)
    {
        return donorService
                .checkEligibility(id);
    }
}