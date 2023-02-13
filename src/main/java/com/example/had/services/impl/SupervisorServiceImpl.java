package com.example.had.services.impl;

import com.example.had.entities.Hospital;
import com.example.had.entities.Supervisor;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.HospitalDto;
import com.example.had.payloads.SupervisorDto;
import com.example.had.repositories.SupervisorRepo;
import com.example.had.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    @Autowired
    SupervisorRepo supervisorRepo;

    @Override
    public SupervisorDto createSupervisor(SupervisorDto supervisorDto) {
        Supervisor supervisor = this.dtoToSupervisor(supervisorDto);
        Supervisor savedSupervisor = this.supervisorRepo.save(supervisor);
        return this.supervisorToDto(savedSupervisor);
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
        supervisor.setHospitalId(supervisorDto.getHospitalId());
        Supervisor updatedSupervisor = this.supervisorRepo.save(supervisor);
        return this.supervisorToDto(updatedSupervisor);
    }

    @Override
    public SupervisorDto getSupervisorById(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        return this.supervisorToDto(supervisor);
    }

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        List<Supervisor> supervisors = this.supervisorRepo.findAll();
        List<SupervisorDto> supervisorDtos = supervisors.stream().map(supervisor -> this.supervisorToDto(supervisor)).collect(Collectors.toList());
        return supervisorDtos;
    }

    @Override
    public void deleteSupervisor(Integer supervisorId) {
        Supervisor supervisor = this.supervisorRepo.findById(supervisorId).orElseThrow(() -> {
            return new ResourceNotFoundException("supervisor", "supervisorId", supervisorId);
        });
        this.supervisorRepo.delete(supervisor);
    }


    public Supervisor dtoToSupervisor(SupervisorDto supervisorDto) {
        Supervisor supervisor = new Supervisor();
        supervisor.setSupervisorId(supervisorDto.getSupervisorId());
        supervisor.setFname(supervisorDto.getFname());
        supervisor.setLname(supervisorDto.getLname());
        supervisor.setGender(supervisorDto.getGender());
        supervisor.setDOB(supervisorDto.getDOB());
        supervisor.setPhoneNo(supervisorDto.getPhoneNo());
        supervisor.setAddress(supervisorDto.getAddress());
        supervisor.setHospitalId(supervisorDto.getHospitalId());
        return supervisor;
    }

    public SupervisorDto supervisorToDto(Supervisor supervisor) {
        SupervisorDto supervisorDto = new SupervisorDto();
        supervisorDto.setSupervisorId(supervisor.getSupervisorId());
        supervisorDto.setFname(supervisor.getFname());
        supervisorDto.setLname(supervisor.getLname());
        supervisorDto.setGender(supervisor.getGender());
        supervisorDto.setDOB(supervisor.getDOB());
        supervisorDto.setPhoneNo(supervisor.getPhoneNo());
        supervisorDto.setAddress(supervisor.getAddress());
        supervisorDto.setHospitalId(supervisor.getHospitalId());
        return supervisorDto;
    }
}
