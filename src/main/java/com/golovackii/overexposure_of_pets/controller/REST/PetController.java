package com.golovackii.overexposure_of_pets.controller.REST;

import com.golovackii.overexposure_of_pets.dto.PetDTO;
import com.golovackii.overexposure_of_pets.model.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PetController {

    PetDTO savePet(Pet pet, Optional<List<MultipartFile>> photos);

    PetDTO updatePet(Pet pet, Optional<List<MultipartFile>> photos);

    PetDTO getPetBuId(Integer id);

    List<PetDTO> getAllPets();

    boolean deletePetById(Integer id);


}
