package com.petshop.service;

import com.petshop.model.Tutor;
import com.petshop.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> getAllTutores() {
        return tutorRepository.findAll();
    }

    public Tutor getTutorById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public Tutor createTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public Tutor updateTutor(Long id, Tutor tutorDetails) {
        Tutor existingTutor = tutorRepository.findById(id).orElse(null);
        if (existingTutor != null) {
            existingTutor.setNome(tutorDetails.getNome());
            existingTutor.setTelefone(tutorDetails.getTelefone());
            return tutorRepository.save(existingTutor);
        }
        return null;
    }

    public boolean deleteTutor(Long id) {
        if (tutorRepository.findById(id).isPresent()) {
            tutorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
