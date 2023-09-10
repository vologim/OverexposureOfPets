package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cat")
public class Cat extends Pet {

    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private BreedCat breedCat;
}
