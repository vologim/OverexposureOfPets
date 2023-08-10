package com.golovackii.overexposure_of_pets.model;

public enum Sex {
    MALE("мальчик"),
    FEMALE("девочка");

    private String title;

    Sex(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "title='" + title + '\'' +
                '}';
    }
}
