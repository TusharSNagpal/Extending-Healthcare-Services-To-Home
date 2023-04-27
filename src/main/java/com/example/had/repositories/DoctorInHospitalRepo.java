package com.example.had.repositories;

import com.example.had.entities.DoctorInHospital;

import com.example.had.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorInHospitalRepo extends JpaRepository<DoctorInHospital, Integer> {
    List<DoctorInHospital> getDoctorInHospitalByHospital(Hospital hospital);
}

