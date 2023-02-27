package com.example.had.services.impl;

import com.example.had.entities.Doctor;
import com.example.had.entities.Patient;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.PatientDto;
import com.example.had.repositories.PatientRepo;
import com.example.had.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = this.modelMapper.map(patientDto, Patient.class);
        Patient savedPatient = this.patientRepo.save(patient);
        return this.modelMapper.map(savedPatient, PatientDto.class);
    }
    @Override
    public String getPhoneNo(Integer pId)
    {
       Patient patient= this.patientRepo.findById(pId).orElseThrow(() -> {
            return new ResourceNotFoundException("patient", "patientId", pId); });
        String phoneNo=patient.getPhoneNo();
        return phoneNo;
    }

}
