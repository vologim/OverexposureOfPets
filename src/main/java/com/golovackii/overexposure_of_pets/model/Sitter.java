package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "sitter")
public class Sitter extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sitter_pet-shelter",
    joinColumns = @JoinColumn(name = "sitter_id"),
    inverseJoinColumns = @JoinColumn(name = "pet_shelter_id"))
    private List<PetShelter> petShelterList;
}
