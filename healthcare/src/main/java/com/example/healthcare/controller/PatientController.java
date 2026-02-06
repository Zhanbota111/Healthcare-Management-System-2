package com.example.healthcare.controller;

import com.example.healthcare.model.Patient;
import com.example.healthcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getSummary() {
        // Сортировка по ID, чтобы новые записи были сверху или снизу
        return jdbcTemplate.queryForList("SELECT * FROM medical_summary_view ORDER BY patient_id DESC");
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        // Логирование для отладки
        System.out.println("Received request to save patient: " + patient.getFirstName());
        return patientRepository.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}