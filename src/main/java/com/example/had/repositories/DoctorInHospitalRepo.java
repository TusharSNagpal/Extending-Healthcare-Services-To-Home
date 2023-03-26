package com.example.had.repositories;

import com.example.had.entities.DoctorInHospital;
import com.example.had.entities.FieldWorkerInHospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorInHospitalRepo extends JpaRepository<DoctorInHospital, Integer> {
}

