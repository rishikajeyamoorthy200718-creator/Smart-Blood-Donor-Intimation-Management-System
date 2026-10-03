package com.blooddonor.blooddonor.entity;

public class MatchResponse {

    private Long donorId;
    private String donorName;
    private String bloodGroup;
    private String city;

    public MatchResponse(
            Long donorId,
            String donorName,
            String bloodGroup,
            String city) {

        this.donorId = donorId;
        this.donorName = donorName;
        this.bloodGroup = bloodGroup;
        this.city = city;
    }

    public Long getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getCity() {
        return city;
    }
}