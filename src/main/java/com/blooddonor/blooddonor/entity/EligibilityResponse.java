package com.blooddonor.blooddonor.entity;

import java.time.LocalDate;

public class EligibilityResponse {

    private Long donorId;
    private String donorName;
    private boolean eligible;
    private LocalDate nextEligibleDate;

    public EligibilityResponse(
            Long donorId,
            String donorName,
            boolean eligible,
            LocalDate nextEligibleDate) {

        this.donorId = donorId;
        this.donorName = donorName;
        this.eligible = eligible;
        this.nextEligibleDate = nextEligibleDate;
    }

    public Long getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public boolean isEligible() {
        return eligible;
    }

    public LocalDate getNextEligibleDate() {
        return nextEligibleDate;
    }
}
