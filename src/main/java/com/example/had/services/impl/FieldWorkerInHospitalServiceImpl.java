package com.example.had.services.impl;

import com.example.had.entities.FieldWorker;
import com.example.had.entities.FieldWorkerInHospital;
import com.example.had.entities.Hospital;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.FieldWorkerInHospitalDto;
import com.example.had.repositories.FieldWorkerInHospitalRepo;
import com.example.had.repositories.FieldWorkerRepo;
import com.example.had.repositories.HospitalRepo;
import com.example.had.services.FieldWorkerInHospitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldWorkerInHospitalServiceImpl implements FieldWorkerInHospitalService {
    @Autowired
    private FieldWorkerInHospitalRepo fieldWorkerInHospitalRepo;
    @Autowired
    private HospitalRepo hospitalRepo;
    @Autowired
    private FieldWorkerRepo fieldWorkerRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FieldWorkerInHospitalDto updateFieldWorkerInHospital(FieldWorkerInHospitalDto fieldWorkerInHospitalDto, Integer fwInHospId) {
        FieldWorkerInHospital fieldWorkerInHospital = this.fieldWorkerInHospitalRepo.findById(fwInHospId).orElseThrow(() -> {
            return new ResourceNotFoundException("FieldWorkerInHospital", "fieldWorkerInHospitalId", fwInHospId);
        });
        Hospital hospital = this.hospitalRepo.findById(fieldWorkerInHospitalDto.getHospital().getHospitalId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Hospital", "hospital", fieldWorkerInHospitalDto.getHospital().getHospitalId());
        });
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fieldWorkerInHospitalDto.getFieldWorker().getFwId()).orElseThrow(() -> {
            return new ResourceNotFoundException("FieldWorkerInHospital", "fieldWorkerInHospitalId",fieldWorkerInHospitalDto.getFieldWorker().getFwId() );
        });
        fieldWorkerInHospital.setFieldWorker(fieldWorker);
        fieldWorkerInHospital.setHospital(hospital);
        //fieldWorkerInHospital.setRegistrationDate(fieldWorkerInHospitalDto.getRegistrationDate());
       // fieldWorkerInHospital.setFwInHosp(fieldWorkerInHospitalDto.getFwInHosp());

        FieldWorkerInHospital updatedFieldWorkerInHospital= this.fieldWorkerInHospitalRepo.save(fieldWorkerInHospital);
        return this.modelMapper.map(updatedFieldWorkerInHospital,  FieldWorkerInHospitalDto.class);
    }

    @Override
    public FieldWorkerInHospitalDto geFieldWorkerInHospitalById(Integer fwInHospId) {
                FieldWorkerInHospital fieldWorkerInHospital = this.fieldWorkerInHospitalRepo.findById(fwInHospId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorkerInHospital", "fieldWorkerInHospitalId", fwInHospId);
        });
        return this.modelMapper.map(fieldWorkerInHospital, FieldWorkerInHospitalDto.class);
    }

    @Override
    public void registerFieldWorker(Integer fwId, Integer hosId) {
        FieldWorkerInHospital fieldWorkerInHospital = new FieldWorkerInHospital();
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> new ResourceNotFoundException("FieldWorker", "fieldworker id", fwId));
        ;
        Hospital hospital = this.hospitalRepo.findById(hosId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "hospital", hosId));
        ;
        fieldWorkerInHospital.setFieldWorker(fieldWorker);
        fieldWorkerInHospital.setHospital(hospital);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fieldWorkerInHospital.setRegistrationDate(formatter.format(date));
        FieldWorkerInHospital fieldWorkerInHospital1 = this.fieldWorkerInHospitalRepo.save(fieldWorkerInHospital);

    }

    @Override
    public List<FieldWorkerInHospitalDto> getFieldWorker(Integer hospitalId) {

        Hospital hospital = this.hospitalRepo.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital", "Hospital Id", hospitalId));
        List<FieldWorkerInHospital> fieldWorkerInHospitals= this.fieldWorkerInHospitalRepo.findAllByHospital(hospital);
        List<FieldWorkerInHospitalDto> fieldWorkerInHospitalDtos = fieldWorkerInHospitals.stream().map(fieldWorkerInHospital -> this.modelMapper.map(fieldWorkerInHospital, FieldWorkerInHospitalDto.class)).collect(Collectors.toList());
        return fieldWorkerInHospitalDtos;

    }


}


