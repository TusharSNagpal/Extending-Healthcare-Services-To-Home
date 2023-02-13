package com.example.had.services.impl;

import com.example.had.entities.Hospital;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.HospitalDto;
import com.example.had.repositories.HospitalRepo;
import com.example.had.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepo hospitalRepo;


    @Override
    public HospitalDto createHospital(HospitalDto hospitalDto) {
        Hospital hospital = this.dtoToHospital(hospitalDto);
        Hospital savedHospital = this.hospitalRepo.save(hospital);
        return this.hospitalToDto(savedHospital);
    }

    @Override
    public HospitalDto updateHospital(HospitalDto hospitalDto, Integer hospitalId) {
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> {
            return new ResourceNotFoundException("Hospital", "hospitalId", hospitalId);
        });
        hospital.setName(hospitalDto.getName());
        hospital.setAddress(hospitalDto.getAddress());
        Hospital updatedHospital = this.hospitalRepo.save(hospital);
        return this.hospitalToDto(updatedHospital);
    }

    @Override
    public HospitalDto getHospitalById(Integer hospitalId) {
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> {
            return new ResourceNotFoundException("hospital", "hospitalId", hospitalId);
        });
        return this.hospitalToDto(hospital);
    }

    @Override
    public List<HospitalDto> getAllHospitals() {
        List<Hospital> hospitals = this.hospitalRepo.findAll();
        List<HospitalDto> hospitalDtos = hospitals.stream().map(hospital -> this.hospitalToDto(hospital)).collect(Collectors.toList());
        return hospitalDtos;
    }

    @Override
    public void deleteHospital(Integer hospitalId) {
        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> {
            return new ResourceNotFoundException("hospital", "hospitalId", hospitalId);
        });
        this.hospitalRepo.delete(hospital);

    }

    public Hospital dtoToHospital(HospitalDto hospitalDto) {
        Hospital hospital = new Hospital();
        hospital.setHospitalId(hospitalDto.getHospitalId());
        hospital.setName(hospitalDto.getName());
        hospital.setAddress(hospitalDto.getAddress());
        return hospital;
    }

    public HospitalDto hospitalToDto(Hospital hospital) {
        HospitalDto hospitalDto = new HospitalDto();
        hospitalDto.setHospitalId(hospital.getHospitalId());
        hospitalDto.setName(hospital.getName());
        hospitalDto.setAddress(hospital.getAddress());
        return hospitalDto;
    }
}
