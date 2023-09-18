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
    public PhoneNumber save(PhoneNumber phoneNumber) {
        return repository.save(phoneNumber);
    }

    @Override
    public PhoneNumber update(PhoneNumber phoneNumber) throws NoEntityException {
        Optional<PhoneNumber> phoneNumberOptional = repository.findById(phoneNumber.getId());
        if(phoneNumberOptional.isPresent()) {
            repository.save(phoneNumber);
            return phoneNumber;
        }
        throw new NoEntityException(getMessage(phoneNumber.getId()));
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
