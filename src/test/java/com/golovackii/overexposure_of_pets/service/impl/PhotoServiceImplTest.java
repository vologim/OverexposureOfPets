package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.repository.PhotoRepository;
import com.golovackii.overexposure_of_pets.util.FileLoader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PhotoServiceImplTest {

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private FileLoader fileLoader;

    @InjectMocks
    private PhotoServiceImpl photoService;

    private MultipartFile file;
    private String fileStorage;
    private String fileAddress;

    @BeforeEach
    public void init() {
        file = new MockMultipartFile("file", "file.jpeg", MediaType.IMAGE_JPEG_VALUE, "Text".getBytes());
        fileStorage = "pet_file_storage";
        fileAddress = fileStorage + "/" + getMultipartFile().getOriginalFilename();
    }

    @Test
    void save() {

        Mockito.when(photoRepository.save(getPhoto()))
                .thenReturn(getPhoto());
        Mockito.when(fileLoader.uploadFile(fileStorage, getMultipartFile()))
                .thenReturn(fileAddress);

        Photo actual = photoService.save(getMultipartFile());

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).isEqualTo(getPhoto());
        Mockito.verify(photoRepository, Mockito.times(1))
                .save(getPhoto());
    }

//    @Test
//    void update() throws NoEntityException {
//        Mockito.when(photoRepository.save(getPhoto()))
//                .thenReturn(getPhoto());
//        Mockito.when(photoRepository.findById(Mockito.anyInt()))
//                .thenReturn(Optional.ofNullable(getPhoto()));
//
//        Photo actual = photoService.update(getPhoto());
//
//        Assertions.assertThat(actual).isNotNull();
//        Assertions.assertThat(actual).isEqualTo(getPhoto());
//        Mockito.verify(photoRepository, Mockito.times(1))
//                .save(getPhoto());
//    }

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
        return Photo.builder().id(0).pathPhoto(fileAddress).build();
    }

    private MultipartFile getMultipartFile() {
        return file;
    }
}