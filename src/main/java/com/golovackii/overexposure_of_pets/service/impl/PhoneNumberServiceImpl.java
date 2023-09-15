package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.PhoneNumber;
import com.golovackii.overexposure_of_pets.repository.PhoneNumberRepository;
import com.golovackii.overexposure_of_pets.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository repository;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    @Override
    public PhoneNumber save(PhoneNumber element) {
        return repository.save(element);
    }

    @Override
    public PhoneNumber update(PhoneNumber element) throws NoEntityException {
        Optional<PhoneNumber> phoneNumber = repository.findById(element.getId());
        if(phoneNumber.isPresent()) {
            repository.save(element);
            return element;
        }
        throw new NoEntityException(getMessage(element.getId()));
    }

    @Override
    public PhoneNumber getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<PhoneNumber> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<PhoneNumber> phoneNumber = repository.findById(id);
        if(phoneNumber.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Phone number id = " + id + " not found!";
    }
}
