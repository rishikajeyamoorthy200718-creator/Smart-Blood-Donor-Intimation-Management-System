package com.blooddonor.blooddonor.controller;

import com.blooddonor.blooddonor.entity.Donor;
import com.blooddonor.blooddonor.repository.DonorRepository;
import com.blooddonor.blooddonor.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final DonorRepository donorRepository;

    public EmailController(
            EmailService emailService,
            DonorRepository donorRepository) {

        this.emailService = emailService;
        this.donorRepository = donorRepository;
    }

    // Existing test email
    @GetMapping("/test")
    public String sendTestEmail() {

        emailService.sendEmail(
                "blooddonorintimationsystem@gmail.com",
                "Blood Donor System Test",
                "Congratulations! Your Spring Boot email service is working."
        );

        return "Test email sent successfully";
    }

    // Send actual notification to a donor
    @PostMapping("/notify/{donorId}")
    public String notifyDonor(@PathVariable Long donorId) {

        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() ->
                        new RuntimeException("Donor not found"));

        String subject = "Urgent Blood Donation Request";

        String body =
                "Dear " + donor.getName() + ",\n\n" +
                        "A blood donation request has been received.\n\n" +
                        "Blood Group: " + donor.getBloodGroup() + "\n" +
                        "Location: " + donor.getCity() + "\n\n" +
                        "You are currently eligible to donate blood.\n" +
                        "If you are available, please consider donating.\n\n" +
                        "Thank you for helping save lives!\n\n" +
                        "Smart Blood Donor Intimation and Management System";

        emailService.sendEmail(
                donor.getEmail(),
                subject,
                body
        );

        return "Notification sent successfully to " + donor.getName();
    }
}