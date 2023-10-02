package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.*;
import com.golovackii.overexposure_of_pets.repository.PetShelterRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetShelterServiceImplTest {

    @Mock
    private Address address;

    @Mock
    private PhoneNumber phoneNumber;

    @Mock
    private Sitter sitter;

    @Mock
    private Pet pet;

    @Mock
    private PetShelterRepository petShelterRepository;

    @InjectMocks
    private PetShelterServiceImpl petShelterService;

    @Test
    void save() {
        Mockito.when(petShelterRepository.save(getPetShelter()))
                .thenReturn(getPetShelter());

        PetShelter actual = petShelterService.save(getPetShelter());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPetShelter());
        Mockito.verify(petShelterRepository, Mockito.times(1))
                .save(getPetShelter());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(petShelterRepository.save(getPetShelter()))
                .thenReturn(getPetShelter());
        Mockito.when(petShelterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPetShelter()));

        PetShelter actual = petShelterService.update(getPetShelter());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPetShelter());
        Mockito.verify(petShelterRepository, Mockito.times(1))
                .save(getPetShelter());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(petShelterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPetShelter()));

        PetShelter actual = petShelterService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPetShelter());
    }

    @Test
    void getAllElements() {
        Mockito.when(petShelterRepository.findAll()).thenReturn(getPetShelterList());

        List<PetShelter> actual = petShelterService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPetShelterList().size());
        Assertions.assertThat(actual).isEqualTo(getPetShelterList());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(petShelterRepository).deleteById(Mockito.anyInt());
        Mockito.when(petShelterRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPetShelter()));

        boolean actual = petShelterService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(petShelterRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<PetShelter> getPetShelterList() {
        PetShelter petShelter1 = PetShelter.builder()
                .id(1)
                .organizationName("Name 1")
                .description("Description 1")
                .pets(List.of(pet))
                .addressList(List.of(address))
                .phoneNumberList(List.of(phoneNumber))
                .sitterList(List.of(sitter))
                .build();

        PetShelter petShelter2 = PetShelter.builder()
                .id(2)
                .organizationName("Name 2")
                .description("Description 2")
                .pets(List.of(pet))
                .addressList(List.of(address))
                .phoneNumberList(List.of(phoneNumber))
                .sitterList(List.of(sitter))
                .build();

        PetShelter petShelter3 = PetShelter.builder()
                .id(3)
                .organizationName("Name 3")
                .description("Description 3")
                .pets(List.of(pet))
                .addressList(List.of(address))
                .phoneNumberList(List.of(phoneNumber))
                .sitterList(List.of(sitter))
                .build();

        return List.of(petShelter1, petShelter2, petShelter3);
    }

    private PetShelter getPetShelter() {
        return getPetShelterList().get(0);
    }
}