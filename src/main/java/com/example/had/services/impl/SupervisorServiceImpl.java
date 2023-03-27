package com.example.had.services.impl;

import com.example.had.entities.Hospital;
import com.example.had.entities.Patient;
import com.example.had.entities.Supervisor;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.HospitalDto;
import com.example.had.payloads.SupervisorDto;
import com.example.had.repositories.HospitalRepo;
import com.example.had.repositories.SupervisorRepo;
import com.example.had.services.SupervisorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HospitalRepo hospitalRepo;


    @Override
    public SupervisorDto createSupervisor(SupervisorDto supervisorDto) {
        Supervisor supervisor = this.modelMapper.map(supervisorDto, Supervisor.class);
        int hospitalId = supervisor.getHospital().getHospitalId();
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "Hospital Id", hospitalId));
        supervisor.setHospital(hospital);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(date);
        supervisor.setRegistrationDate(currentDate);
        supervisor.setDob(supervisorDto.getDob().substring(0, 10));
        Supervisor savedSupervisor = this.supervisorRepo.save(supervisor);
        return this.modelMapper.map(savedSupervisor, SupervisorDto.class);
    }

    @Override
    public SupervisorDto updateSupervisor(SupervisorDto supervisorDto, Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("Supervisor", "supervisorId", supervisorId);
        });
        supervisor.setFname(supervisorDto.getFname());
        supervisor.setLname(supervisorDto.getLname());
        supervisor.setGender(supervisorDto.getGender());
        supervisor.setDob(supervisorDto.getDob());
        supervisor.setPhoneNo(supervisorDto.getPhoneNo());
        supervisor.setAddress(supervisorDto.getAddress());
        int hospitalId = supervisorDto.getHospital().getHospitalId();
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "Hospital Id", hospitalId));
        supervisor.setHospital(hospital);
        supervisor.setRegistrationDate(supervisorDto.getRegistrationDate());
        Supervisor updatedSupervisor = this.supervisorRepo.save(supervisor);
        return this.modelMapper.map(updatedSupervisor, SupervisorDto.class);
    }

    @Override
    public SupervisorDto getSupervisorById(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        return this.modelMapper.map(supervisor, SupervisorDto.class);
    }

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        List<Supervisor> supervisors = this.supervisorRepo.findAll();
        List<SupervisorDto> supervisorDtos = supervisors.stream().map(supervisor -> this.modelMapper.map(supervisor, SupervisorDto.class)).collect(Collectors.toList());
        return supervisorDtos;
    }

    @Override
    public void deleteSupervisor(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        this.supervisorRepo.delete(supervisor);
    }

    @Override
    public String getPhoneNo(Integer sId)
    {
        Supervisor supervisor= this.supervisorRepo.findById(sId).orElseThrow(() -> {
            return new ResourceNotFoundException("patient", "patientId", sId); });
        String phoneNo=supervisor.getPhoneNo();
        return phoneNo;
    }
}
