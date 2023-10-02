package com.golovackii.overexposure_of_pets.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_address_id")
    private Address registrationAddress;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "passport_photo",
    joinColumns = @JoinColumn(name = "passport_id"),
    inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos = new ArrayList<>();
}
