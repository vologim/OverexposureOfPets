package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Address;
import com.golovackii.overexposure_of_pets.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void save() {
        Mockito.when(addressRepository.save(getAddress()))
                .thenReturn(getAddress());

        Address actual = addressService.save(getAddress());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual)
                .isEqualTo(getAddress());
        Mockito.verify(addressRepository, Mockito.times(1))
                .save(getAddress());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(addressRepository.save(getAddress()))
                .thenReturn(getAddress());
        Mockito.when(addressRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getAddress()));

        Address actual = addressService.update(getAddress());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual)
                .isEqualTo(getAddress());
        Mockito.verify(addressRepository, Mockito.times(1))
                .save(getAddress());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(addressRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getAddress()));

        Address actual = addressService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getAddress());
    }

    @Test
    void getAllElements() {
        Mockito.when(addressRepository.findAll())
                .thenReturn(getAddresses());

        List<Address> actual = addressService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getAddresses().size());
        Assertions.assertThat(actual).isEqualTo(getAddresses());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(addressRepository).deleteById(Mockito.anyInt());
        Mockito.when(addressRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getAddress()));

        boolean actual = addressService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(addressRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Address> getAddresses() {
        Address address1 = Address.builder()
                .id(1)
                .country("Belarus")
                .city("Minsk")
                .street("Nemiga")
                .houseNumber("20")
                .apartmentNumber("32")
                .build();

        Address address2 = Address.builder()
                .id(2)
                .country("Belarus")
                .city("Minsk")
                .street("Nezavisimosti")
                .houseNumber("134")
                .apartmentNumber("94")
                .build();

        Address address3 = Address.builder()
                .id(3)
                .country("Belarus")
                .city("Minsk")
                .street("Oleshova")
                .houseNumber("90")
                .apartmentNumber("112")
                .build();

        return List.of(address1, address2, address3);
    }

    private Address getAddress() {
        return getAddresses().get(0);
    }
}