package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
