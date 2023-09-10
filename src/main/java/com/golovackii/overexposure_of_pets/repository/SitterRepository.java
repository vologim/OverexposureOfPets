package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Sitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SitterRepository extends JpaRepository<Sitter, Integer> {
}
