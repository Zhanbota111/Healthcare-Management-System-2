package com.example.healthcare.controller;

import com.example.healthcare.model.MedicalProfessional;
import com.example.healthcare.repository.MedicalProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_professionals")
public class MedicalProfessionalController {

    @Autowired
    private MedicalProfessionalRepository medicalProfessionalRepository;

    @GetMapping
    public List<MedicalProfessional> getAllProfessionals() {
        return medicalProfessionalRepository.findAll();
    }

    @PostMapping
    public MedicalProfessional createProfessional(@RequestBody MedicalProfessional professional) {
        return medicalProfessionalRepository.save(professional);
    }

    @GetMapping("/{id}")
    public MedicalProfessional getProfessionalById(@PathVariable Long id) {
        return medicalProfessionalRepository.findById(id).orElseThrow(() -> new RuntimeException("Medical Professional not found"));
    }

    @PutMapping("/{id}")
    public MedicalProfessional updateProfessional(@PathVariable Long id, @RequestBody MedicalProfessional professionalDetails) {
        MedicalProfessional professional = medicalProfessionalRepository.findById(id).orElseThrow(() -> new RuntimeException("Medical Professional not found"));
        professional.setName(professionalDetails.getName());
        professional.setSpecialization(professionalDetails.getSpecialization());
        professional.setHospital(professionalDetails.getHospital());
        return medicalProfessionalRepository.save(professional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        medicalProfessionalRepository.deleteById(id);
    }
}
