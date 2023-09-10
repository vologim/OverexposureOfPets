package com.golovackii.overexposure_of_pets.mapper;

import com.golovackii.overexposure_of_pets.dto.CatDTO;
import com.golovackii.overexposure_of_pets.model.Cat;
import org.springframework.stereotype.Component;

@Component
public class CatMapper {
    public CatDTO toDTO(Cat cat) {
        return CatDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .petDateBirth(cat.getPetDateBirth())
                .sex(cat.getSex())
                .description(cat.getDescription())
                .photos(cat.getPhotos())
                .breedCat(cat.getBreedCat())
                .build();
    }
    public Cat toCat(CatDTO catDTO) {
        return Cat.builder()
                .id(catDTO.getId())
                .name(catDTO.getName())
                .petDateBirth(catDTO.getPetDateBirth())
                .sex(catDTO.getSex())
                .description(catDTO.getDescription())
                .photos(catDTO.getPhotos())
                .breedCat(catDTO.getBreedCat())
                .build();
    }
}
