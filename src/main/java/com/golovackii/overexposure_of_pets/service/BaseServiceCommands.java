package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;

import java.util.List;

public interface BaseServiceCommands<T> {

    T save(T element);

    T update(T element) throws NoEntityException;

    T getById(Integer id) throws NoEntityException;

    List<T> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;

}
