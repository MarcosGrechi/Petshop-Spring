package com.petshop.repository;

import com.petshop.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PetRepository {

    private List<Pet> pets = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(); // Para gerar IDs únicos

    public List<Pet> findAll() {
        return pets;
    }

    public Optional<Pet> findById(Long id) {
        return pets.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(idCounter.incrementAndGet()); // Gera um novo ID
        }
        Optional<Pet> existingPetOpt = findById(pet.getId());
        if (existingPetOpt.isPresent()) {
            int index = pets.indexOf(existingPetOpt.get());
            pets.set(index, pet); // Substitui o pet existente
        } else {
            pets.add(pet); // Adiciona um novo pet
        }
        return pet;
    }

    public void deleteById(Long id) {
        pets.removeIf(p -> p.getId().equals(id));
    }
}
