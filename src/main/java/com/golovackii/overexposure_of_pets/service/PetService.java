package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.model.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetService {

    Pet save(Pet pet);

    Pet save(Pet pet, List<MultipartFile> photos);

    Pet update(Pet pet);

    Pet update(Pet pet, List<MultipartFile> photos);

    Pet getById(Integer id);

    List<Pet> getAllElements();

    boolean deleteById(Integer id);

}
