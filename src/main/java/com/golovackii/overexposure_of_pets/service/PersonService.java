package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    Person update(Person person) throws NoEntityException;

    Person getById(Integer id) throws NoEntityException;

    List<Person> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
