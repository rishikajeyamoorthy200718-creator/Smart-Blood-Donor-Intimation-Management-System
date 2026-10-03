package com.blooddonor.blooddonor.service;

import java.time.LocalDate;
import com.blooddonor.blooddonor.entity.EligibilityResponse;
import com.blooddonor.blooddonor.entity.Donor;
import com.blooddonor.blooddonor.repository.DonorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.blooddonor.blooddonor.entity.MatchResponse;

@Service
public class DonorService {

    private final DonorRepository donorRepository;
    private final EmailService emailService;

    public DonorService(
            DonorRepository donorRepository,
            EmailService emailService) {

        this.donorRepository = donorRepository;
        this.emailService = emailService;
    }

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id).orElse(null);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
    public EligibilityResponse checkEligibility(Long donorId)
    {
        Donor donor =
                donorRepository.findById(donorId)
                        .orElseThrow();
        LocalDate lastDonationDate =
                donor.getLastDonationDate();

        LocalDate nextEligibleDate =
                lastDonationDate.plusDays(90);

        boolean eligible =
                !LocalDate.now()
                        .isBefore(nextEligibleDate);

        return new EligibilityResponse(
                donor.getId(),
                donor.getName(),
                eligible,
                nextEligibleDate
        );
    }
    public List<MatchResponse> findEligibleDonors(
            String bloodGroup,
            String city)
    {

        return donorRepository
                .findByBloodGroupAndCity(
                        bloodGroup,
                        city
                )
                .stream()

                .filter(donor -> {

                    LocalDate nextEligibleDate =
                            donor.getLastDonationDate()
                                    .plusDays(90);

                    return !LocalDate.now()
                            .isBefore(nextEligibleDate);

                })

                .peek(donor -> {

                    emailService.sendEmail(
                            donor.getEmail(),
                            "Urgent Blood Donation Request",
                            "Dear " + donor.getName() +
                                    ",\n\nA patient requires blood group " +
                                    donor.getBloodGroup() +
                                    " in " + donor.getCity() +
                                    ".\n\nPlease contact the blood bank if you are available to donate."
                    );

                })

                .map(donor ->
                        new MatchResponse(
                                donor.getId(),
                                donor.getName(),
                                donor.getBloodGroup(),
                                donor.getCity()
                        ))

                .collect(Collectors.toList());

    }


}
