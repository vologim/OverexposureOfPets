package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {
}
