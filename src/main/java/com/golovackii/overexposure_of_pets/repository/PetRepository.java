package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
