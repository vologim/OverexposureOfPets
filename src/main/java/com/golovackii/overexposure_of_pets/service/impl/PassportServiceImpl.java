package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Passport;
import com.golovackii.overexposure_of_pets.repository.PassportRepository;
import com.golovackii.overexposure_of_pets.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepository repository;

    @Autowired
    public PassportServiceImpl(PassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passport save(Passport element) {
        return repository.save(element);
    }

    @Override
    public Passport update(Passport element) throws NoEntityException {
        return repository.save(repository.findById(element.getId())
                .orElseThrow(() -> new NoEntityException(getMessage(element.getId()))));
    }

    @Override
    public Passport getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Passport> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Passport> passport = repository.findById(id);
        if(passport.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Passport id = " + id + " not found!";
    }
}