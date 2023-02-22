package com.example.had.services;

import com.example.had.payloads.PatientDto;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDto);
    int searchPatient(int patientId);
}