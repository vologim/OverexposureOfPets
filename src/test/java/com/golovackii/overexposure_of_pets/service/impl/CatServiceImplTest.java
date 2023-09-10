package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.BreedCat;
import com.golovackii.overexposure_of_pets.model.Cat;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.model.Sex;
import com.golovackii.overexposure_of_pets.repository.CatRepository;
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
class CatServiceImplTest {

    @Mock
    private CatRepository catRepository;
    @Mock
    private Photo photo;

    @InjectMocks
    private CatServiceImpl catService;

    @Test
    void save() {
        Mockito.when(catRepository.save(getCat()))
                .thenReturn(getCat());

        Cat actual = catService.save(getCat());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getCat());
        Mockito.verify(catRepository, Mockito.times(1))
                .save(getCat());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(catRepository.save(getCat()))
                .thenReturn(getCat());
        Mockito.when(catRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getCat()));

        Cat actual = catService.update(getCat());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getCat());
        Mockito.verify(catRepository, Mockito.times(1))
                .save(getCat());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(catRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getCat()));

        Cat actual = catService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getCat());
    }

    @Test
    void getAllElements() {
        Mockito.when(catRepository.findAll()).thenReturn(getCats());

        List<Cat> actual = catService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getCats().size());
        Assertions.assertThat(actual).isEqualTo(getCats());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(catRepository).deleteById(Mockito.anyInt());
        Mockito.when(catRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getCat()));

        boolean actual = catService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(catRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Cat> getCats() {
        Cat cat1 = Cat.builder()
                .id(1)
                .name("Cat 1")
                .petDateBirth(LocalDate.of(2022, 12, 31))
                .sex(Sex.FEMALE)
                .color("Yellow")
                .description("Description 1")
                .photos(List.of(photo))
                .breedCat(BreedCat.BENGAL)
                .build();

        Cat cat2 = Cat.builder()
                .id(2)
                .name("Cat 2")
                .petDateBirth(LocalDate.of(2021, 10, 21))
                .sex(Sex.MALE)
                .color("Black")
                .description("Description 2")
                .photos(List.of(photo))
                .breedCat(BreedCat.METIS)
                .build();

        Cat cat3 = Cat.builder()
                .id(3)
                .name("Cat 3")
                .petDateBirth(LocalDate.of(2020, 8, 10))
                .sex(Sex.FEMALE)
                .color("White")
                .description("Description 3")
                .photos(List.of(photo))
                .breedCat(BreedCat.SPHYNX)
                .build();

        return List.of(cat1, cat2, cat3);
    }

    private Cat getCat() {
        return getCats().get(0);
    }
}