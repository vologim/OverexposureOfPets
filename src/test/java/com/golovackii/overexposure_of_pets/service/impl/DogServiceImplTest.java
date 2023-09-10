package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.BreedDog;
import com.golovackii.overexposure_of_pets.model.Dog;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.model.Sex;
import com.golovackii.overexposure_of_pets.repository.DogRepository;
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
class DogServiceImplTest {

    @Mock
    private DogRepository dogRepository;

    @Mock
    private Photo photo;

    @InjectMocks
    private DogServiceImpl dogService;

    @Test
    void save() {
        Mockito.when(dogRepository.save(getDog()))
                .thenReturn(getDog());

        Dog actual = dogService.save(getDog());

        Assertions.assertThat(actual).isEqualTo(getDog());
        Mockito.verify(dogRepository, Mockito.times(1))
                .save(getDog());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(dogRepository.save(getDog())).thenReturn(getDog());
        Mockito.when(dogRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getDog()));

        Dog actual = dogService.update(getDog());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getDog());
        Mockito.verify(dogRepository, Mockito.times(1))
                .save(getDog());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(dogRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getDog()));

        Dog actual = dogService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getDog());
    }

    @Test
    void getAllElements() {
        Mockito.when(dogRepository.findAll()).thenReturn(getDogs());

        List<Dog> actual = dogService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getDogs().size());
        Assertions.assertThat(actual).isEqualTo(getDogs());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(dogRepository).deleteById(Mockito.anyInt());
        Mockito.when(dogRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getDog()));

        boolean actual = dogService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(dogRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Dog> getDogs() {
        Dog dog1 = Dog.builder()
                .id(1)
                .name("Dog 1")
                .petDateBirth(LocalDate.of(2018, 3, 22))
                .sex(Sex.FEMALE)
                .color("White")
                .description("Description 1")
                .photos(List.of(photo))
                .breedDog(BreedDog.DOBERMAN)
                .build();

        Dog dog2 = Dog.builder()
                .id(2)
                .name("Dog 2")
                .petDateBirth(LocalDate.of(2015, 1, 25))
                .sex(Sex.MALE)
                .color("Black")
                .description("Description 2")
                .photos(List.of(photo))
                .breedDog(BreedDog.MASTIFF)
                .build();

        Dog dog3 = Dog.builder()
                .id(3)
                .name("Dog 3")
                .petDateBirth(LocalDate.of(2015, 12, 22))
                .sex(Sex.MALE)
                .color("White")
                .description("Description 3")
                .photos(List.of(photo))
                .breedDog(BreedDog.PUG)
                .build();

        return List.of(dog1, dog2, dog3);
    }

    private Dog getDog() {
        return getDogs().get(0);
    }
}