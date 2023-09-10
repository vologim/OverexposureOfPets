package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.repository.PhotoRepository;
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
class PhotoServiceImplTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoServiceImpl photoService;

    @Test
    void save() {
        Mockito.when(photoRepository.save(getPhoto()))
                .thenReturn(getPhoto());

        Photo actual = photoService.save(getPhoto());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoto());
        Mockito.verify(photoRepository, Mockito.times(1))
                .save(getPhoto());
    }

    @Test
    void update() throws NoEntityException {
        Mockito.when(photoRepository.save(getPhoto()))
                .thenReturn(getPhoto());
        Mockito.when(photoRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoto()));

        Photo actual = photoService.update(getPhoto());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoto());
        Mockito.verify(photoRepository, Mockito.times(1))
                .save(getPhoto());
    }

    @Test
    void getById() throws NoEntityException {
        Mockito.when(photoRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoto()));

        Photo actual = photoService.getById(Mockito.anyInt());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoto());
    }

    @Test
    void getAllElements() {
        Mockito.when(photoRepository.findAll()).thenReturn(getPhotos());

        List<Photo> actual = photoService.getAllElements();

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.size()).isEqualTo(getPhotos().size());
        Assertions.assertThat(actual).isEqualTo(getPhotos());
    }

    @Test
    void deleteById() throws NoEntityException {
        Mockito.doNothing().when(photoRepository).deleteById(Mockito.anyInt());
        Mockito.when(photoRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.ofNullable(getPhoto()));

        boolean actual = photoService.deleteById(Mockito.anyInt());

        Assertions.assertThat(actual).isTrue();
        Mockito.verify(photoRepository, Mockito.times(1))
                .deleteById(Mockito.anyInt());
    }

    private List<Photo> getPhotos() {
        Photo photo1 = Photo.builder()
                .id(1)
                .pathPhoto("Path 1")
                .build();

        Photo photo2 = Photo.builder()
                .id(2)
                .pathPhoto("Path 2")
                .build();

        Photo photo3 = Photo.builder()
                .id(3)
                .pathPhoto("Path 3")
                .build();

        return List.of(photo1, photo2, photo3);
    }

    private Photo getPhoto() {
        return getPhotos().get(0);
    }
}