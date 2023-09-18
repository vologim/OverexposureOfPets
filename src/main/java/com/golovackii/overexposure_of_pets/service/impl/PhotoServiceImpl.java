package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.FormatFileException;
import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.repository.PhotoRepository;
import com.golovackii.overexposure_of_pets.service.PhotoService;
import com.golovackii.overexposure_of_pets.util.CheckFileForPermission;
import com.golovackii.overexposure_of_pets.util.FileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository repository;
    private final FileLoader fileLoader;
    private static final String PET_FILE_STORAGE = "pet_file_storage";

    @Autowired
    public PhotoServiceImpl(PhotoRepository repository, FileLoader fileLoader) {
        this.repository = repository;
        this.fileLoader = fileLoader;
    }

    @Override
    public Photo save(MultipartFile photo) {
        if(CheckFileForPermission.checkForJPG(photo.getOriginalFilename())) {
            String pathPhoto = fileLoader.uploadFile(PET_FILE_STORAGE, photo);
            return repository.save(Photo.builder()
                    .pathPhoto(pathPhoto)
                    .build());
        }
        throw new FormatFileException(getMessageCheckImage());
    }

//    @Override
//    public Photo update(Photo photo) throws NoEntityException {
//        Optional<Photo> optionalPhoto = repository.findById(photo.getId());
//        if(optionalPhoto.isPresent()) {
//            repository.save(photo);
//            return photo;
//        }
//        throw new NoEntityException(getMessage(photo.getId()));
//    }

    @Override
    public Photo getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessageNotFound(id)));
    }

    @Override
    public List<Photo> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Photo> photo = repository.findById(id);
        if(photo.isPresent()) {
            fileLoader.deleteFile(photo.get().getPathPhoto());
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessageNotFound(id));
    }

    private String getMessageNotFound(int id) {
        return "Photo id = " + id + " not found!";
    }

    private String getMessageCheckImage() {
        return "The file should be .jpg or .jpeg!";
    }
}
