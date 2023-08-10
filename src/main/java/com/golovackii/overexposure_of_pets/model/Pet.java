package com.golovackii.overexposure_of_pets.model;

import java.time.LocalDate;
import java.util.List;

abstract public class Pet {

    private int id;
    private String name;
    private LocalDate petDateBirth;
    private Sex sex;
    private String color;
    private String description;
    private List<User> petOwnerList;
    private Photo photo;

}
