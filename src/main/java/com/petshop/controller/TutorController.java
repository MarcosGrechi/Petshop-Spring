package com.petshop.controller;

import com.petshop.model.Tutor;
import com.petshop.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public List<Tutor> getAllTutores() {
        return tutorService.getAllTutores();
    }

    @GetMapping("/{id}")
    public Tutor getTutorById(@PathVariable Long id) {
        return tutorService.getTutorById(id);
    }

    @PostMapping
    public Tutor createTutor(@RequestBody Tutor tutor) {
        return tutorService.createTutor(tutor);
    }

    @PutMapping("/{id}")
    public Tutor updateTutor(@PathVariable Long id, @RequestBody Tutor tutorDetails) {
        return tutorService.updateTutor(id, tutorDetails);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTutor(@PathVariable Long id) {
        return tutorService.deleteTutor(id);
    }
}
