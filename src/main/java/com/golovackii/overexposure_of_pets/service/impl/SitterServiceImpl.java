package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Sitter;
import com.golovackii.overexposure_of_pets.repository.SitterRepository;
import com.golovackii.overexposure_of_pets.service.SitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SitterServiceImpl implements SitterService {

    private final SitterRepository repository;

    @Autowired
    public SitterServiceImpl(SitterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sitter save(Sitter element) {
        return repository.save(element);
    }

    @Override
    public Sitter update(Sitter element) throws NoEntityException {
        Optional<Sitter> sitter = repository.findById(element.getId());
        if(sitter.isPresent()) {
            repository.save(element);
            return element;
        }
        throw new NoEntityException(getMessage(element.getId()));
    }

    @Override
    public Sitter getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Sitter> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Sitter> sitter = repository.findById(id);
        if(sitter.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int elementId) {
        return "Sitter id = " + elementId + " not found!";
    }
}
