package com.example.had.services.impl;

import com.example.had.entities.Doctor;
import com.example.had.entities.Hospital;
import com.example.had.entities.Patient;
import com.example.had.entities.Visit;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.VisitDto;
import com.example.had.repositories.DoctorRepo;
import com.example.had.repositories.HospitalRepo;
import com.example.had.repositories.PatientRepo;
import com.example.had.repositories.VisitRepo;
import com.example.had.services.VisitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    VisitRepo visitRepo;

    @Autowired
    HospitalRepo hospitalRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createVisit(VisitDto visitDto) {
        Visit visit  = this.modelMapper.map(visitDto, Visit.class);
        int hospitalId = visit.getHospital().getHospitalId();
        int patientId = visit.getPatient().getPatientId();
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(()->new ResourceNotFoundException("Hospital","hospital id",hospitalId));;
        Patient patient = this.patientRepo.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient","patient id",patientId));;
        visit.setIsActive(1);
        visit.setHospital(hospital);
        visit.setPatient(patient);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        visit.setVisitDate(formatter.format(date));
        Visit addedVisit = this.visitRepo.save(visit);
    }

    @Override
    public List<VisitDto> activeVisits(int hospitalId) {
//        List<Visit> activeVisits = this.visitRepo.findActiveVisits(hospitalId);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(date);
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(()->new ResourceNotFoundException("Hospital","hospital id",hospitalId));;
        List<Visit> activeVisits = this.visitRepo.findAllByIsActiveAndVisitDateAndHospital(1,currentDate,hospital);
        List<VisitDto> activeVisitsDtos = activeVisits.stream().map((activeVisit -> this.modelMapper.map(activeVisit, VisitDto.class))).collect(Collectors.toList());
        return activeVisitsDtos;
    }
}
