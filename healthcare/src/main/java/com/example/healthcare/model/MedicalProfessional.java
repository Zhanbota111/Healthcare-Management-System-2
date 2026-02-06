package com.example.healthcare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_professional")
public class MedicalProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String specialization;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public MedicalProfessional() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }
}
