package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "dog")
public class Dog extends Pet {

    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private BreedDog breedDog;
}
