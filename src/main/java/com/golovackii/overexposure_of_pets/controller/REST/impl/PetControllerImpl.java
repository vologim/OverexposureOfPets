package com.golovackii.overexposure_of_pets.controller.REST.impl;

import com.golovackii.overexposure_of_pets.controller.REST.PetController;
import com.golovackii.overexposure_of_pets.dto.PetDTO;
import com.golovackii.overexposure_of_pets.mapper.PetMapper;
import com.golovackii.overexposure_of_pets.model.Pet;
import com.golovackii.overexposure_of_pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets/")
public class PetControllerImpl implements PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @Autowired
    public PetControllerImpl(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @Override
    @PostMapping
    public PetDTO savePet(@RequestPart(name = "pet") Pet pet,
                          @RequestPart(name = "photos", required = false) Optional<List<MultipartFile>> photos) {
        if(photos.isPresent()) {
            return petMapper.toDTO(petService.save(pet, photos.get()));
        }
        else {
            return petMapper.toDTO(petService.save(pet));
        }
    }

    @Override
    @PutMapping
    public PetDTO updatePet(@RequestPart(name = "pet") Pet pet,
                            @RequestPart(name = "photos", required = false) Optional<List<MultipartFile>> photos) {
        if(photos.isPresent()) {
            return petMapper.toDTO(petService.update(pet, photos.get()));
        }
        else {
            return petMapper.toDTO(petService.update(pet));
        }
    }

    @Override
    @GetMapping("/{id}")
    public PetDTO getPetBuId(@PathVariable Integer id) {
        return petMapper.toDTO(petService.getById(id));
    }

    @Override
    @GetMapping
    public List<PetDTO> getAllPets() {
        return petService
                .getAllElements()
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deletePetById(@PathVariable Integer id) {
        return petService.deleteById(id);
    }
}
