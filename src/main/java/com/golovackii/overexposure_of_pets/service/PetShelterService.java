package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.PetShelter;

import java.util.List;

public interface PetShelterService {
    PetShelter save(PetShelter petShelter);

    PetShelter update(PetShelter petShelter) throws NoEntityException;

    PetShelter getById(Integer id) throws NoEntityException;

    List<PetShelter> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
