package com.golovackii.overexposure_of_pets.service;

import com.golovackii.overexposure_of_pets.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Photo save(MultipartFile photo);

//    Photo update(Photo photo);

    Photo getById(Integer id);

    List<Photo> getAllElements();

    boolean deleteById(Integer id);
}
