package com.petshop.controller;

import com.petshop.model.Pet;
import com.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails, @RequestParam(required = false) Long tutorId) {
        return petService.updatePet(id, petDetails, tutorId);
    }

    @DeleteMapping("/{id}")
    public boolean deletePet(@PathVariable Long id) {
        return petService.deletePet(id);
    }

    @PutMapping("/{petId}/tutor/{tutorId}")
    public Pet assignTutorToPet(@PathVariable Long petId, @PathVariable Long tutorId) {
        return petService.assignTutorToPet(petId, tutorId);
    }
}
