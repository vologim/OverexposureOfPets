package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.BreedPet;
import com.golovackii.overexposure_of_pets.model.Pet;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.model.Sex;
import com.golovackii.overexposure_of_pets.repository.PetRepository;
import com.golovackii.overexposure_of_pets.service.PhotoService;
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
class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PhotoService photoService;

    @Mock
    private Photo photo;

    @InjectMocks
    private PetServiceImpl petService;

    @Test
    void save() {
        Mockito.when(petRepository.save(getPet()))
                .thenReturn(getPet());

        Pet actual = petService.save(getPet());

        Assertions.assertThat(actual).isEqualTo(getPet());
        Mockito.verify(petRepository, Mockito.times(1))
                .save(getPet());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(petRepository.save(getPet())).thenReturn(getPet());
        Mockito.when(petRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPet()));

        Pet actual = petService.update(getPet());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPet());
        Mockito.verify(petRepository, Mockito.times(1))
                .save(getPet());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(petRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPet()));

        Pet actual = petService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPet());
    }

    @Test
    void getAllElements() {
        Mockito.when(petRepository.findAll()).thenReturn(getPets());

        List<Pet> actual = petService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPets().size());
        Assertions.assertThat(actual).isEqualTo(getPets());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(petRepository).deleteById(Mockito.anyInt());
        Mockito.when(petRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPet()));
        Mockito.when(photoService.deleteById(Mockito.anyInt()))
                .thenReturn(true);

        boolean actual = petService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(petRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Pet> getPets() {
        Pet dog1 = Pet.builder()
                .id(1)
                .name("Cat 1")
                .petDateBirth(LocalDate.of(2018, 3, 22))
                .sex(Sex.FEMALE)
                .color("White")
                .description("Description 1")
                .photos(List.of(photo))
                .breedPet(BreedPet.BENGAL)
                .build();

        Pet dog2 = Pet.builder()
                .id(2)
                .name("Dog 1")
                .petDateBirth(LocalDate.of(2015, 1, 25))
                .sex(Sex.MALE)
                .color("Black")
                .description("Description 2")
                .photos(List.of(photo))
                .breedPet(BreedPet.MASTIFF)
                .build();

        Pet dog3 = Pet.builder()
                .id(3)
                .name("Cat 2")
                .petDateBirth(LocalDate.of(2015, 12, 22))
                .sex(Sex.MALE)
                .color("White")
                .description("Description 3")
                .photos(List.of(photo))
                .breedPet(BreedPet.PUG)
                .build();

        return List.of(dog1, dog2, dog3);
    }

    private Pet getPet() {
        return getPets().get(0);
    }
}