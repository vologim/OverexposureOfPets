package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.repository.PhotoRepository;
import com.golovackii.overexposure_of_pets.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository repository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Photo save(Photo element) {
        return repository.save(element);
    }

    @Override
    public Photo update(Photo element) throws NoEntityException {
        Optional<Photo> photo = repository.findById(element.getId());
        if(photo.isPresent()) {
            repository.save(element);
            return element;
        }
        throw new NoEntityException(getMessage(element.getId()));
    }

    @Override
    public Photo getById(Integer id) throws NoEntityException {
        return repository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Photo> getAllElements() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Photo> photo = repository.findById(id);
        if(photo.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Photo id = " + id + " not found!";
    }
}
