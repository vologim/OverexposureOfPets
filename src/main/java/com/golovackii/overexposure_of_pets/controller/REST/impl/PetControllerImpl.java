package com.golovackii.overexposure_of_pets.controller.REST.impl;

import com.golovackii.overexposure_of_pets.controller.REST.PetController;
import com.golovackii.overexposure_of_pets.dto.PetDTO;
import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.mapper.PetMapper;
import com.golovackii.overexposure_of_pets.model.Pet;
import com.golovackii.overexposure_of_pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public PetDTO saveElement(@RequestBody Pet element) {
        return petMapper.toDTO(petService.save(element));
    }

    @Override
    @PutMapping
    public PetDTO updateElement(@RequestBody Pet element) throws NoEntityException {
        return petMapper.toDTO(petService.update(element));
    }

    @Override
    @GetMapping("/{id}")
    public PetDTO getElementBuId(@PathVariable Integer id) throws NoEntityException {
        return petMapper.toDTO(petService.getById(id));
    }

    @Override
    @GetMapping
    public List<PetDTO> getAllElements() {
        return petService
                .getAllElements()
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteElementById(@PathVariable Integer id) throws NoEntityException {
        return petService.deleteById(id);
    }
}
