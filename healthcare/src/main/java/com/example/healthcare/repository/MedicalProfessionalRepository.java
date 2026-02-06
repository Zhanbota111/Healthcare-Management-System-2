package com.example.healthcare.repository;

import com.example.healthcare.model.MedicalProfessional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalProfessionalRepository extends JpaRepository<MedicalProfessional, Long> {
}
