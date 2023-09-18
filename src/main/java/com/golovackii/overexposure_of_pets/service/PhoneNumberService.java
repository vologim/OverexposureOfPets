package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {
    PhoneNumber save(PhoneNumber phoneNumber);

    PhoneNumber update(PhoneNumber phoneNumber) throws NoEntityException;

    PhoneNumber getById(Integer id) throws NoEntityException;

    List<PhoneNumber> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
