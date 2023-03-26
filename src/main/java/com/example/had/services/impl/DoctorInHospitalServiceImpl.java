package com.example.had.services.impl;

import com.example.had.entities.*;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.DoctorInHospitalDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;
import com.example.had.repositories.*;
import com.example.had.services.DoctorInHospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class DoctorInHospitalServiceImpl implements DoctorInHospitalService{
    @Autowired
    private DoctorInHospitalRepo doctorInHospitalRepo;
    @Autowired
    private HospitalRepo hospitalRepo;
    @Autowired
    private DoctorRepo doctorRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DoctorInHospitalDto updateDoctorInHospital(DoctorInHospitalDto doctorInHospitalDto, Integer docInHospId) {
        DoctorInHospital doctorInHospital = this.doctorInHospitalRepo.findById(docInHospId).orElseThrow(() -> {
            return new ResourceNotFoundException("DoctorInHospital", "doctorInHospitalId", docInHospId);
        });
        Hospital hospital = this.hospitalRepo.findById(doctorInHospitalDto.getHospital().getHospitalId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Hospital", "hospital", doctorInHospitalDto.getHospital().getHospitalId());
        });
        Doctor doctor= this.doctorRepo.findById(doctorInHospitalDto.getDoctor().getDoctorId()).orElseThrow(() -> {
            return new ResourceNotFoundException("DoctorInHospital", "doctorInHospitalId",doctorInHospitalDto.getDoctor().getDoctorId() );
        });
        doctorInHospital.setDoctor(doctor);
        doctorInHospital.setHospital(hospital);

        DoctorInHospital updatedDoctorInHospital= this.doctorInHospitalRepo.save(doctorInHospital);
        return this.modelMapper.map(updatedDoctorInHospital,  DoctorInHospitalDto.class);
    }

    @Override
    public DoctorInHospitalDto getDoctorInHospitalById(Integer docInHospId) {
        DoctorInHospital doctorInHospital = this.doctorInHospitalRepo.findById(docInHospId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorkerInHospital", "fieldWorkerInHospitalId", docInHospId);
        });
        return this.modelMapper.map(doctorInHospital, DoctorInHospitalDto.class);
    }

    @Override
    public void registerDoctor(Integer docId, Integer hosId) {
        DoctorInHospital doctorInHospital = new DoctorInHospital();
       Doctor doctor = this.doctorRepo.findById(docId).orElseThrow(() -> new ResourceNotFoundException("Doctor", "doctor id", docId));
        ;
        Hospital hospital = this.hospitalRepo.findById(hosId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "hospital", hosId));
        ;
        doctorInHospital.setDoctor(doctor);
        doctorInHospital.setHospital(hospital);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        doctorInHospital.setRegistrationDate(formatter.format(date));
        DoctorInHospital doctorInHospital1 = this.doctorInHospitalRepo.save(doctorInHospital);

    }


    @Override
    public String getPhoneNo(Integer sId) {
        Doctor doctor= this.doctorRepo.findById(sId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", sId);
        });
        String phoneNo = doctor.getPhoneNo();
        return phoneNo;
    }


}
