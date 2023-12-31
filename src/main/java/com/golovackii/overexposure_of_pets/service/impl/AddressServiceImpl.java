package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Address;
import com.golovackii.overexposure_of_pets.repository.AddressRepository;
import com.golovackii.overexposure_of_pets.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public Address update(Address address) throws NoEntityException {
        Optional<Address> addressOptional = repository.findById(address.getId());
        if(addressOptional.isPresent()) {
            repository.save(address);
            return address;
        }
        throw new NoEntityException(getMessage(address.getId()));
    }

    @Override
    public Address getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Address> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Address> address = repository.findById(id);
        if(address.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Address id = " + id + " not found!";
    }
}
