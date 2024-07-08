package com.petshop.service;

import com.petshop.model.Pet;
import com.petshop.model.Tutor;
import com.petshop.repository.PetRepository;
import com.petshop.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public Pet createPet(Pet pet) {
        if (pet.getTutorId() != null) {
            Tutor tutor = tutorRepository.findById(pet.getTutorId()).orElse(null);
            if (tutor != null) {
                pet.setTutor(tutor);
            }
        }
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet petDetails, Long tutorId) {
        Pet existingPet = petRepository.findById(id).orElse(null);
        if (existingPet != null) {
            existingPet.setNome(petDetails.getNome());
            existingPet.setTipo(petDetails.getTipo());
            existingPet.setIdade(petDetails.getIdade());

            if (tutorId != null) {
                Tutor tutor = tutorRepository.findById(tutorId).orElse(null);
                if (tutor != null) {
                    existingPet.setTutor(tutor);
                    existingPet.setTutorId(tutorId); // Adiciona o tutorId no pet
                }
            }

            return petRepository.save(existingPet);
        }
        return null;
    }

    public boolean deletePet(Long id) {
        if (petRepository.findById(id).isPresent()) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Pet assignTutorToPet(Long petId, Long tutorId) {
        Pet pet = petRepository.findById(petId).orElse(null);
        Tutor tutor = tutorRepository.findById(tutorId).orElse(null);
        if (pet != null && tutor != null) {
            pet.setTutor(tutor);
            pet.setTutorId(tutorId); // Adiciona o tutorId no pet
            return petRepository.save(pet);
        }
        return null;
    }
}
