package com.golovackii.overexposure_of_pets.controller.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.golovackii.overexposure_of_pets.exception.NoEntityException;

import java.util.List;

public interface BaseControllerCommands<T,E> {

    T saveElement(E model);

    T updateElement(E model) throws NoEntityException;

    T getElementBuId(Integer id) throws NoEntityException;

    List<T> getAllElements();

    boolean deleteElementById(Integer id) throws NoEntityException;
}
