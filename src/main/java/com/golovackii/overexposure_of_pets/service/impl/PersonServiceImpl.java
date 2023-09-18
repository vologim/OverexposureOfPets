package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Person;
import com.golovackii.overexposure_of_pets.repository.PersonRepository;
import com.golovackii.overexposure_of_pets.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Person elepersonent) throws NoEntityException {
        Optional<Person> personOptional = repository.findById(elepersonent.getId());
        if(personOptional.isPresent()) {
            repository.save(elepersonent);
            return elepersonent;
        }
        throw new NoEntityException(getMessage(elepersonent.getId()));
    }

    @Override
    public Person getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Person> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Person> person = repository.findById(id);
        if(person.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Person id = " + id + " not found!";
    }
}
