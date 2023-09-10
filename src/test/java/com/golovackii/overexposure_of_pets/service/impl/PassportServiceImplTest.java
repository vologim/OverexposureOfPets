package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Passport;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.model.Sex;
import com.golovackii.overexposure_of_pets.repository.PassportRepository;
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
class PassportServiceImplTest {

    @Mock
    private Photo photo;

    @Mock
    private PassportRepository passportRepository;

    @InjectMocks
    private PassportServiceImpl passportService;

    @Test
    void save() {
        Mockito.when(passportRepository.save(getPassport()))
                .thenReturn(getPassport());

        Passport actual = passportService.save(getPassport());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPassport());
        Mockito.verify(passportRepository, Mockito.times(1))
                .save(getPassport());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(passportRepository.save(getPassport()))
                .thenReturn(getPassport());
        Mockito.when(passportRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPassport()));

        Passport actual = passportService.update(getPassport());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPassport());
        Mockito.verify(passportRepository, Mockito.times(1))
                .save(getPassport());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(passportRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPassport()));

        Passport actual = passportService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPassport());
    }

    @Test
    void getAllElements() {
        Mockito.when(passportRepository.findAll()).thenReturn(getPassports());

        List<Passport> actual = passportService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPassports().size());
        Assertions.assertThat(actual).isEqualTo(getPassports());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(passportRepository).deleteById(Mockito.anyInt());
        Mockito.when(passportRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPassport()));

        boolean actual = passportService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(passportRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Passport> getPassports() {
        Passport passport1 = Passport.builder()
                .id(1)
                .firstName("First name 1")
                .lastName("Last name 1")
                .passportNumber("111111")
                .sex(Sex.FEMALE)
                .photos(List.of(photo))
                .build();

        Passport passport2 = Passport.builder()
                .id(2)
                .firstName("First name 2")
                .lastName("Last name 2")
                .passportNumber("222222")
                .sex(Sex.MALE)
                .photos(List.of(photo))
                .build();

        Passport passport3 = Passport.builder()
                .id(3)
                .firstName("First name 3")
                .lastName("Last name 3")
                .passportNumber("333333")
                .sex(Sex.FEMALE)
                .photos(List.of(photo))
                .build();

        return List.of(passport1, passport2, passport3);
    }

    private Passport getPassport() {
        return getPassports().get(0);
    }
}