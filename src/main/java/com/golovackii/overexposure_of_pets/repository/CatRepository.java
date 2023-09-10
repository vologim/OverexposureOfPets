package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}
