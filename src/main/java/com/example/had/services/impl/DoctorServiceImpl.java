package com.example.had.services.impl;

import com.example.had.entities.Doctor;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.DoctorDto;
import com.example.had.payloads.DoctorDto;
import com.example.had.repositories.DoctorRepo;
import com.example.had.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = this.modelMapper.map(doctorDto, Doctor.class);
        Doctor savedDoctor = this.doctorRepo.save(doctor);
        return this.modelMapper.map(savedDoctor, DoctorDto.class);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, Integer doctorId) {
        Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> {
            return new ResourceNotFoundException("Doctor", "doctorId", doctorId);
        });
        doctor.setFname(doctorDto.getFname());
        doctor.setLname(doctorDto.getLname());
        doctor.setGender(doctorDto.getGender());
        doctor.setDOB(doctorDto.getDOB());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setPhoneNo(doctorDto.getPhoneNo());
        doctor.setRegistrationDate(doctorDto.getRegistrationDate());
        Doctor updatedDoctor = this.doctorRepo.save(doctor);
        return this.modelMapper.map(updatedDoctor, DoctorDto.class);
    }

    @Override
    public DoctorDto getDoctorById(Integer doctorId) {
        Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", doctorId);
        });
        return this.modelMapper.map(doctor, DoctorDto.class);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = this.doctorRepo.findAll();
        List<DoctorDto> doctorDtos = doctors.stream().map(doctor -> this.modelMapper.map(doctor, DoctorDto.class)).collect(Collectors.toList());
        return doctorDtos;
    }

    @Override
    public void deleteDoctor(Integer doctorId) {
        Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", doctorId);
        });
        this.doctorRepo.delete(doctor);
    }
    @Override
    public String getPhoneNo(Integer dId)
    {
        Doctor doctor = this.doctorRepo.findById(dId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", dId); });
        String phoneNo=doctor.getPhoneNo();
        return phoneNo;
    }


}
