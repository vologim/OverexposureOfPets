package com.golovackii.overexposure_of_pets.controller.REST.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.golovackii.overexposure_of_pets.controller.REST.CatController;
import com.golovackii.overexposure_of_pets.dto.CatDTO;
import com.golovackii.overexposure_of_pets.exception.NoEntityException;
import com.golovackii.overexposure_of_pets.mapper.CatMapper;
import com.golovackii.overexposure_of_pets.model.Cat;
import com.golovackii.overexposure_of_pets.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cats/")
public class CatControllerImpl implements CatController {

    private final CatService catService;
    private final CatMapper catMapper;

    @Autowired
    public CatControllerImpl(CatService catService, CatMapper catMapper) {
        this.catService = catService;
        this.catMapper = catMapper;
    }

    @Override
    @PostMapping
    public CatDTO saveElement(@RequestBody Cat element) throws JsonProcessingException {
        return catMapper.toDTO(catService.save(element));
    }

    @Override
    @PutMapping
    public CatDTO updateElement(@RequestBody Cat element) throws JsonProcessingException, NoEntityException {
        return catMapper.toDTO(catService.update(element));
    }

    @Override
    @GetMapping("/{id}")
    public CatDTO getElementBuId(@PathVariable Integer id) throws NoEntityException {
        return catMapper.toDTO(catService.getById(id));
    }

    @Override
    @GetMapping
    public List<CatDTO> getAllElements() {
        return catService
                .getAllElements()
                .stream()
                .map(catMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @DeleteMapping
    public boolean deleteElementById(@PathVariable Integer id) throws NoEntityException {
        return catService.deleteById(id);
    }
}
