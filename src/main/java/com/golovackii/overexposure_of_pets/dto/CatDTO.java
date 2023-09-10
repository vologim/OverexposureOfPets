package com.golovackii.overexposure_of_pets.dto;

import com.golovackii.overexposure_of_pets.model.BreedCat;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatDTO {

    private int id;
    private String name;
    private LocalDate petDateBirth;
    private Sex sex;
    private String description;
    private List<Photo> photos;
    private BreedCat breedCat;
}
