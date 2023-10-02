package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.*;
import com.golovackii.overexposure_of_pets.repository.SitterRepository;
import com.golovackii.overexposure_of_pets.service.SitterService;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SitterServiceImplTest {

    @Mock
    private PhoneNumber phoneNumber;

    @Mock
    private Address address;

    @Mock
    private Pet pet;

    @Mock
    private Passport passport;

    @Mock
    private PetShelter petShelter;

    @Mock
    private SitterRepository sitterRepository;

    @InjectMocks
    private SitterServiceImpl sitterService;

    @Test
    void save() {
        Mockito.when(sitterRepository.save(getSitter()))
                .thenReturn(getSitter());

        Sitter actual = sitterService.save(getSitter());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getSitter());
        Mockito.verify(sitterRepository, Mockito.times(1))
                .save(getSitter());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(sitterRepository.save(getSitter()))
                .thenReturn(getSitter());
        Mockito.when(sitterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getSitter()));

        Sitter actual = sitterService.update(getSitter());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getSitter());
        Mockito.verify(sitterRepository, Mockito.times(1))
                .save(getSitter());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(sitterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getSitter()));

        Sitter actual = sitterService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getSitter());
    }

    @Test
    void getAllElements() {
        Mockito.when(sitterRepository.findAll()).thenReturn(getSitters());

        List<Sitter> actual = sitterService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getSitters().size());
        Assertions.assertThat(actual).isEqualTo(getSitters());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(sitterRepository).deleteById(Mockito.anyInt());
        Mockito.when(sitterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getSitter()));

        boolean actual = sitterService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(sitterRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Sitter> getSitters() {
        Sitter sitter1 = Sitter.builder()
                .id(1)
                .firstName("Name 1")
                .surname("Surname 1")
                .description("Description 1")
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .passport(passport)
                .petShelter(petShelter)
                .build();

        Sitter sitter2 = Sitter.builder()
                .id(2)
                .firstName("Name 2")
                .surname("Surname 2")
                .description("Description 2")
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .passport(passport)
                .petShelter(petShelter)
                .build();

        Sitter sitter3 = Sitter.builder()
                .id(3)
                .firstName("Name 3")
                .surname("Surname 3")
                .description("Description 3")
                .phoneNumbers(List.of(phoneNumber))
                .address(address)
                .pets(List.of(pet))
                .passport(passport)
                .petShelter(petShelter)
                .build();

        return List.of(sitter1, sitter2, sitter3);
    }

    private Sitter getSitter() {
        return getSitters().get(0);
    }
}