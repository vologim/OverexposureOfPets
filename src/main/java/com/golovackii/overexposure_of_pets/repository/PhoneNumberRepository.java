package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
}
