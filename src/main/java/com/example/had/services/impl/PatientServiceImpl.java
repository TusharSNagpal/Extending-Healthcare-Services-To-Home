package com.example.had.services.impl;

import com.example.had.entities.Hospital;
import com.example.had.entities.Patient;
import com.example.had.entities.Supervisor;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.PatientDto;
import com.example.had.repositories.HospitalRepo;
import com.example.had.repositories.PatientRepo;
import com.example.had.repositories.SupervisorRepo;
import com.example.had.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = this.modelMapper.map(patientDto, Patient.class);
        int hospitalId = patient.getHospital().getHospitalId();
        int supervisorId = patient.getSupervisor().getSupervisorId();

        //find hospital by hospitalId: using repo

        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(()->new ResourceNotFoundException("Hospital", "hospital id", hospitalId));
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(()->new ResourceNotFoundException("Supervisor","supervisor id", supervisorId));

        patient.setHospital(hospital);
        patient.setSupervisor(supervisor);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        patient.setRegistrationDate(formatter.format(date));

        Patient savedPatient = this.patientRepo.save(patient);
        return this.modelMapper.map(savedPatient, PatientDto.class);
    }
}
