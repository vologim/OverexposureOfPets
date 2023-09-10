package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Cat;
import com.golovackii.overexposure_of_pets.repository.CatRepository;
import com.golovackii.overexposure_of_pets.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    private final CatRepository repository;

    @Autowired
    public CatServiceImpl(CatRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cat save(Cat element) {
        return repository.save(element);
    }

    @Override
    public Cat update(Cat element) throws NoEntityException {
        return repository.save(repository.findById(element.getId()).orElseThrow(
                () -> new NoEntityException(getMessage(element.getId()))));
    }

    @Override
    public Cat getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Cat> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Cat> cat = repository.findById(id);
        if(cat.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Cat id = " + id + " not found!";
    }
}
