package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number_id")
    private List<PhoneNumber> phoneNumbers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sitter_pet-shelter",
            joinColumns = @JoinColumn(name = "pet_shelter_id"),
            inverseJoinColumns = @JoinColumn(name = "sitter_id"))
    private List<Sitter> sitterList;
}
