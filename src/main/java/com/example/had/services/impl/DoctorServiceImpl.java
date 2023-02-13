package com.example.had.services.impl;

import com.example.had.entities.Doctor;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.DoctorDto;
import com.example.had.payloads.DoctorDto;
import com.example.had.repositories.DoctorRepo;
import com.example.had.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepo doctorRepo;
    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = this.dtoToDoctor(doctorDto);
        Doctor savedDoctor = this.doctorRepo.save(doctor);
        return this.doctorToDto(savedDoctor);
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
        doctor.setPhoneNo(doctorDto.getPhoneNo());
        doctor.setAddress(doctorDto.getAddress());
        Doctor updatedDoctor = this.doctorRepo.save(doctor);
        return this.doctorToDto(updatedDoctor);
    }

    @Override
    public DoctorDto getDoctorById(Integer doctorId) {
        Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", doctorId);
        });
        return this.doctorToDto(doctor);
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = this.doctorRepo.findAll();
        List<DoctorDto> doctorDtos = doctors.stream().map(doctor -> this.doctorToDto(doctor)).collect(Collectors.toList());
        return doctorDtos;
    }

    @Override
    public void deleteDoctor(Integer doctorId) {
        Doctor doctor = this.doctorRepo.findById(doctorId).orElseThrow(() -> {
            return new ResourceNotFoundException("doctor", "doctorId", doctorId);
        });
        this.doctorRepo.delete(doctor);
    }

    public Doctor dtoToDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorDto.getDoctorId());
        doctor.setFname(doctorDto.getFname());
        doctor.setLname(doctorDto.getLname());
        doctor.setGender(doctorDto.getGender());
        doctor.setDOB(doctorDto.getDOB());
        doctor.setPhoneNo(doctorDto.getPhoneNo());
        doctor.setAddress(doctorDto.getAddress());
        return doctor;
    }

    public DoctorDto doctorToDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setDoctorId(doctor.getDoctorId());
        doctorDto.setFname(doctor.getFname());
        doctorDto.setLname(doctor.getLname());
        doctorDto.setGender(doctor.getGender());
        doctorDto.setDOB(doctor.getDOB());
        doctorDto.setPhoneNo(doctor.getPhoneNo());
        doctorDto.setAddress(doctor.getAddress());
        return doctorDto;
    }
}
