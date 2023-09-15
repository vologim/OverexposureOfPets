package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pet_date_birth")
    private LocalDate petDateBirth;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "breed")
    @Enumerated(EnumType.STRING)
    private BreedPet breedPet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "photo_pet",
    joinColumns = @JoinColumn(name = "pet_id"),
    inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos;
}