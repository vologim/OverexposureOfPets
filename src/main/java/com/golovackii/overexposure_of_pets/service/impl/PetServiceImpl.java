package com.golovackii.overexposure_of_pets.service.impl;

import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.model.Pet;
import com.golovackii.overexposure_of_pets.model.Photo;
import com.golovackii.overexposure_of_pets.repository.PetRepository;
import com.golovackii.overexposure_of_pets.service.PetService;
import com.golovackii.overexposure_of_pets.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PhotoService photoService;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, PhotoService photoService) {
        this.petRepository = petRepository;
        this.photoService = photoService;
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet save(Pet pet, List<MultipartFile> photos) {
        for(MultipartFile multipartFile : photos) {
            pet.getPhotos().add(photoService.save(multipartFile));
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet update(Pet pet) throws NoEntityException {
        Optional<Pet> petOptional = petRepository.findById(pet.getId());
        if(petOptional.isPresent()) {
            petRepository.save(petOptional.get());
            return petOptional.get();
        }
        throw new NoEntityException(getMessage(pet.getId()));
    }

    @Override
    public Pet update(Pet pet, List<MultipartFile> photos) throws NoEntityException {
        Optional<Pet> petOptional = petRepository.findById(pet.getId());
        if(petOptional.isPresent()) {
            for(MultipartFile multipartFile : photos) {
                petOptional.get().getPhotos().add(photoService.save(multipartFile));
            }
            petRepository.save(petOptional.get());
            return petOptional.get();
        }
        throw new NoEntityException(getMessage(pet.getId()));
    }

    @Override
    public Pet getById(Integer id) {
        return petRepository.findById(id).orElseThrow(
                () -> new NoEntityException(getMessage(id)));
    }

    @Override
    public List<Pet> getAllElements() {
        return petRepository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws NoEntityException {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()) {
            for(Photo photo : pet.get().getPhotos()) {
                photoService.deleteById(photo.getId());
            }
            petRepository.deleteById(id);
            return true;
        }
        throw new NoEntityException(getMessage(id));
    }

    private String getMessage(int id) {
        return "Pet id = " + id + " not found!";
    }

}
