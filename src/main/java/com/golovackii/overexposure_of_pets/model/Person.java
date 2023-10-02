package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_numberPhone",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "number_phone_id"))
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "person",
            cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pet> pets = new ArrayList<>();

}
