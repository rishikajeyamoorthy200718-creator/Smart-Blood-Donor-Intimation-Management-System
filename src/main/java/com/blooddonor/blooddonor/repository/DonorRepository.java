package com.blooddonor.blooddonor.repository;

import com.blooddonor.blooddonor.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByBloodGroupAndCity(
            String bloodGroup,
            String city
    );
}