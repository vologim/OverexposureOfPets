package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    Address update(Address address) throws NoEntityException;

    Address getById(Integer id) throws NoEntityException;

    List<Address> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
