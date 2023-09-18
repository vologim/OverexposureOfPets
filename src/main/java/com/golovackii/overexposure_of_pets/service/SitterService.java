package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Sitter;

import java.util.List;

public interface SitterService {
    Sitter save(Sitter sitter);

    Sitter update(Sitter sitter) throws NoEntityException;

    Sitter getById(Integer id) throws NoEntityException;

    List<Sitter> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
