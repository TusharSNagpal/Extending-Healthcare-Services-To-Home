package com.example.had.services.impl;

import com.example.had.entities.Hospital;
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
        supervisor.setDOB(supervisorDto.getDOB());
        supervisor.setPhoneNo(supervisorDto.getPhoneNo());
        supervisor.setAddress(supervisorDto.getAddress());
        supervisor.setHospital(supervisorDto.getHospital());
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
}
