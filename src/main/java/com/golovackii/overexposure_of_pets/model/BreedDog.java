package com.golovackii.overexposure_of_pets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
public enum BreedDog {

    BULLDOG("Бульдог"),
    CANES("Гончие"),
    DOBERMAN("Доберман"),
    MASTIFF("Мастиф"),
    PUG("Мопс"),
    DACHSHUND("Такса"),
    MONGREL("Дворняжка");

    private final String title;

    BreedDog(String title) {
        this.title = title;
    }
}
