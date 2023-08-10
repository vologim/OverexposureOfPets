package com.golovackii.overexposure_of_pets.model;

import java.time.LocalDate;
import java.util.List;

abstract public class User {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private List<PhoneNumber> phoneNumbers;
    private Address address;
    private List<Pet> pets;
}
