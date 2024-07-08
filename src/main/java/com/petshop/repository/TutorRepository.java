package com.petshop.repository;

import com.petshop.model.Tutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TutorRepository {

    private List<Tutor> tutores = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(); // Para gerar IDs únicos

    public List<Tutor> findAll() {
        return tutores;
    }

    public Optional<Tutor> findById(Long id) {
        return tutores.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Tutor save(Tutor tutor) {
        if (tutor.getId() == null) {
            tutor.setId(idCounter.incrementAndGet()); // Gera um novo ID
        }
        Optional<Tutor> existingTutorOpt = findById(tutor.getId());
        if (existingTutorOpt.isPresent()) {
            int index = tutores.indexOf(existingTutorOpt.get());
            tutores.set(index, tutor); // Substitui o tutor existente
        } else {
            tutores.add(tutor); // Adiciona um novo tutor
        }
        return tutor;
    }

    public void deleteById(Long id) {
        tutores.removeIf(t -> t.getId().equals(id));
    }
}
