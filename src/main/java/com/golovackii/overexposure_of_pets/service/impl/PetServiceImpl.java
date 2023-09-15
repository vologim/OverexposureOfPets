package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Pet;
import com.golovackii.overexposure_of_pets.repository.PetRepository;
import com.golovackii.overexposure_of_pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Autowired
    public PetServiceImpl(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pet save(Pet element) {
        return repository.save(element);
    }

    @Override
    public Pet update(Pet element) throws NoEntityException {
        Optional<Pet> pet = repository.findById(element.getId());
        if(pet.isPresent()) {
            repository.save(element);
            return element;
        }
        throw new NoEntityException(getMessage(element.getId()));
    }

    @Override
    public Pet getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Pet> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Pet> pet = repository.findById(id);
        if(pet.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Pet id = " + id + " not found!";
    }
}
