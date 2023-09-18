package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Photo save(MultipartFile photo);

//    Photo update(Photo photo) throws NoEntityException;

    Photo getById(Integer id) throws NoEntityException;

    List<Photo> getAllElements();

    boolean deleteById(Integer id) throws NoEntityException;
}
