package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Passport;

import java.util.List;

public interface PassportService {
    Passport save(Passport passport);

    Passport update(Passport passport) throws NoEntityException;

    Passport getById(Integer id) throws NoEntityException;

    List<Passport> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
