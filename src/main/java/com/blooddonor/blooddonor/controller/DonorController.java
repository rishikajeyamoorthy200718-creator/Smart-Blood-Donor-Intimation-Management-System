package com.blooddonor.blooddonor.controller;

import com.blooddonor.blooddonor.entity.Donor;
import com.blooddonor.blooddonor.service.DonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public Donor getDonor(@PathVariable Long id) {
        return donorService.getDonorById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return "Donor Deleted Successfully";
    }
}