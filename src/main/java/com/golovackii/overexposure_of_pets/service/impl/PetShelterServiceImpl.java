package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.PetShelter;
import com.golovackii.overexposure_of_pets.repository.PetShelterRepository;
import com.golovackii.overexposure_of_pets.service.PetShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetShelterServiceImpl implements PetShelterService {

    private final PetShelterRepository repository;

    @Autowired
    public PetShelterServiceImpl(PetShelterRepository repository) {
        this.repository = repository;
    }

    @Override
    public PetShelter save(PetShelter element) {
        return repository.save(element);
    }

    @Override
    public PetShelter update(PetShelter element) throws NoEntityException {
        Optional<PetShelter> petShelter = repository.findById(element.getId());
        if(petShelter.isPresent()) {
            repository.save(element);
            return element;
        }
        throw new NoEntityException(getMessage(element.getId()));
    }

    @Override
    public PetShelter getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<PetShelter> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<PetShelter> petShelter = repository.findById(id);
        if(petShelter.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Pet shelter id = " + id + " not found!";
    }
}
