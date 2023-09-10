package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.*;
import com.golovackii.overexposure_of_pets.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PhoneNumber phoneNumber;
    @Mock
    private Address address;
    @Mock
    private Pet pet;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void save() {
        Mockito.when(personRepository.save(getPerson()))
                .thenReturn(getPerson());

        Person actual = personService.save(getPerson());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPerson());
        Mockito.verify(personRepository, Mockito.times(1))
                .save(getPerson());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(personRepository.save(getPerson()))
                .thenReturn(getPerson());
        Mockito.when(personRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPerson()));

        Person actual = personService.update(getPerson());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPerson());
        Mockito.verify(personRepository, Mockito.times(1))
                .save(getPerson());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(personRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPerson()));

        Person actual = personService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPerson());
    }

    @Test
    void getAllElements() {
        Mockito.when(personRepository.findAll()).thenReturn(getPersons());

        List<Person> actual = personService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPersons().size());
        Assertions.assertThat(actual).isEqualTo(getPersons());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(personRepository).deleteById(Mockito.anyInt());
        Mockito.when(personRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPerson()));

        boolean actual = personService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(personRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Person> getPersons() {
        Person person1 = Person.builder()
                .id(1)
                .firstName("Name 1")
                .lastName("Last name 1")
                .dateBirth(LocalDate.of(1985, 11, 9))
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .build();

        Person person2 = Person.builder()
                .id(2)
                .firstName("Name 2")
                .lastName("Last name 2")
                .dateBirth(LocalDate.of(1982, 2, 9))
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .build();

        Person person3 = Person.builder()
                .id(3)
                .firstName("Name 3")
                .lastName("Last name 3")
                .dateBirth(LocalDate.of(1993, 11, 9))
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .build();

        return List.of(person1, person2, person3);
    }

    private Person getPerson() {
        return getPersons().get(0);
    }
}