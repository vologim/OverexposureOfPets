package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
}
