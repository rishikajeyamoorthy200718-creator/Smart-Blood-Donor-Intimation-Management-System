package com.blooddonor.blooddonor.controller;

import com.blooddonor.blooddonor.entity.MatchResponse;
import com.blooddonor.blooddonor.service.DonorService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final DonorService donorService;

    public MatchController(
            DonorService donorService)
    {
        this.donorService = donorService;
    }

    @GetMapping
    public List<MatchResponse> findDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city)
    {
        return donorService
                .findEligibleDonors(
                        bloodGroup,
                        city
                );
    }
}
