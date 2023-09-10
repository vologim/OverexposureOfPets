package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
