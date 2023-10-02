package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pet_shelter")
public class PetShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "petShelter",
    cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Sitter> sitterList = new ArrayList<>();

    @OneToMany(mappedBy = "petShelter",
    cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petShelter_phoneNumber",
    joinColumns = @JoinColumn(name = "pet_shelter_id"),
    inverseJoinColumns = @JoinColumn(name = "phone_number_id"))
    private List<PhoneNumber> phoneNumberList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petShelter_address",
    joinColumns = @JoinColumn(name = "pet_shelter_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petShelter_photo",
    joinColumns = @JoinColumn(name = "pet_shelter_id"),
    inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new LinkedList<>();
}
