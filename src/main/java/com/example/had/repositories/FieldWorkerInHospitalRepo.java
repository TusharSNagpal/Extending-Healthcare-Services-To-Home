package com.example.had.repositories;

import com.example.had.entities.FieldWorkerInHospital;

import com.example.had.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldWorkerInHospitalRepo extends JpaRepository<FieldWorkerInHospital, Integer> {
    List<FieldWorkerInHospital> findAllByHospital(Hospital hospital);
