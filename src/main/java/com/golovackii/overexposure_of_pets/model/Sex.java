package com.golovackii.overexposure_of_pets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
public enum Sex {
    MALE("муж"),
    FEMALE("жен");

    private final String title;

    Sex(String title) {
        this.title = title;
    }
}
