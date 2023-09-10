package com.golovackii.overexposure_of_pets.repository;

import com.golovackii.overexposure_of_pets.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
