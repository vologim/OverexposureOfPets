package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "sitter")
public class Sitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pet_shelter_id")
    private PetShelter petShelter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sitter_photo",
    joinColumns = @JoinColumn(name = "sitter_id"),
    inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sitter_phoneNumber",
    joinColumns = @JoinColumn(name = "sitter_id"),
    inverseJoinColumns = @JoinColumn(name = "phone_number_id"))
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "sitter",
            cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pet> pets = new ArrayList<>();

}
