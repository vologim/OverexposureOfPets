package com.golovackii.overexposure_of_pets.mapper;

import com.golovackii.overexposure_of_pets.dto.PetDTO;
import com.golovackii.overexposure_of_pets.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    public PetDTO toDTO(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .petDateBirth(pet.getPetDateBirth())
                .sex(pet.getSex())
                .description(pet.getDescription())
                .photos(pet.getPhotos())
                .breedPet(pet.getBreedPet())
                .color(pet.getColor())
                .build();
    }
    public Pet toPet(PetDTO petDTO) {
        return Pet.builder()
                .id(petDTO.getId())
                .name(petDTO.getName())
                .petDateBirth(petDTO.getPetDateBirth())
                .sex(petDTO.getSex())
                .description(petDTO.getDescription())
                .photos(petDTO.getPhotos())
                .breedPet(petDTO.getBreedPet())
                .color(petDTO.getColor())
                .build();
    }
}
