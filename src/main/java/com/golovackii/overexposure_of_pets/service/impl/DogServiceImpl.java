package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Dog;
import com.golovackii.overexposure_of_pets.repository.DogRepository;
import com.golovackii.overexposure_of_pets.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;

    @Autowired
    public DogServiceImpl(DogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dog save(Dog element) {
        return repository.save(element);
    }

    @Override
    public Dog update(Dog element) throws NoEntityException {
        return repository.save(repository.findById(element.getId()).orElseThrow(
                () -> new NoEntityException(getMessage(element.getId()))));
    }

    @Override
    public Dog getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Dog> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Dog> dog = repository.findById(id);
        if(dog.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Dog id = " + id + " not found!";
    }
}
