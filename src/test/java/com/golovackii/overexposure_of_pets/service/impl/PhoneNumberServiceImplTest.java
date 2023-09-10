package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.PhoneNumber;
import com.golovackii.overexposure_of_pets.repository.PhoneNumberRepository;
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
class PhoneNumberServiceImplTest {

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    private PhoneNumberServiceImpl phoneNumberService;

    @Test
    void save() {
        Mockito.when(phoneNumberRepository.save(getPhoneNumber()))
                .thenReturn(getPhoneNumber());

        PhoneNumber actual = phoneNumberService.save(getPhoneNumber());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoneNumber());
        Mockito.verify(phoneNumberRepository, Mockito.times(1))
                .save(getPhoneNumber());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(phoneNumberRepository.save(getPhoneNumber()))
                .thenReturn(getPhoneNumber());
        Mockito.when(phoneNumberRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoneNumber()));

        PhoneNumber actual = phoneNumberService.update(getPhoneNumber());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoneNumber());
        Mockito.verify(phoneNumberRepository, Mockito.times(1))
                .save(getPhoneNumber());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(phoneNumberRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoneNumber()));

        PhoneNumber actual = phoneNumberService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoneNumber());
    }

    @Test
    void getAllElements() {
        Mockito.when(phoneNumberRepository.findAll()).thenReturn(getPhoneNumbers());

        List<PhoneNumber> actual = phoneNumberService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPhoneNumbers().size());
        Assertions.assertThat(actual).isEqualTo(getPhoneNumbers());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(phoneNumberRepository).deleteById(Mockito.anyInt());
        Mockito.when(phoneNumberRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoneNumber()));

        boolean actual = phoneNumberService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(phoneNumberRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<PhoneNumber> getPhoneNumbers() {
        PhoneNumber phoneNumber1 = PhoneNumber.builder()
                .id(1)
                .phoneNumber("1111111")
                .build();

        PhoneNumber phoneNumber2 = PhoneNumber.builder()
                .id(1)
                .phoneNumber("2222222")
                .build();

        PhoneNumber phoneNumber3 = PhoneNumber.builder()
                .id(1)
                .phoneNumber("333333333")
                .build();

        return List.of(phoneNumber1, phoneNumber2, phoneNumber3);
    }

    private PhoneNumber getPhoneNumber() {
        return getPhoneNumbers().get(0);
    }
}