package com.golovackii.overexposure_of_pets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
public enum BreedCat {
    METIS("Метис"),
    BENGAL("Бенгальская"),
    PERSIAN("Персидская"),
    SIAMESE("Сиамская"),
    SIBERIAN("Сибирская"),
    SPHYNX("Сфинкс");

    private final String title;

    BreedCat(String title) {
        this.title = title;
    }
}
