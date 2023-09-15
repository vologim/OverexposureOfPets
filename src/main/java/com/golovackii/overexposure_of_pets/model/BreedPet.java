package com.golovackii.overexposure_of_pets.model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum BreedPet {
    METIS("Метис"),
    BENGAL("Бенгальская"),
    PERSIAN("Персидская"),
    SIAMESE("Сиамская"),
    SIBERIAN("Сибирская"),
    SPHYNX("Сфинкс"),

    //dogs
    BULLDOG("Бульдог"),
    CANES("Гончие"),
    DOBERMAN("Доберман"),
    MASTIFF("Мастиф"),
    PUG("Мопс"),
    DACHSHUND("Такса"),
    MONGREL("Дворняжка");

    private final String title;

    BreedPet(String title) {
        this.title = title;
    }
}
