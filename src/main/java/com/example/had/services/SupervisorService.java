package com.example.had.services;

import com.example.had.payloads.SupervisorDto;

import java.util.List;

public interface SupervisorService {

    SupervisorDto createSupervisor(SupervisorDto supervisorDto);
    SupervisorDto updateSupervisor(SupervisorDto supervisorDto, Integer supervisorID);
    SupervisorDto getSupervisorById(Integer supervisorId);
    List<SupervisorDto> getAllSupervisors();
    void deleteSupervisor(Integer supervisorId);
    String getPhoneNo(Integer sId);
}
