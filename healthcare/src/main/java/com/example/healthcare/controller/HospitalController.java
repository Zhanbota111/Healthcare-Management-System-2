package com.example.healthcare.controller;

import com.example.healthcare.model.Hospital;
import com.example.healthcare.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
}
