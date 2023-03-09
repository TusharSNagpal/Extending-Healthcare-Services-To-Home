package com.example.had.services.impl;

import com.example.had.entities.FieldWorker;
import com.example.had.entities.Patient;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.FieldWorkerDto;
import com.example.had.repositories.FieldWorkerRepo;
import com.example.had.services.FieldWorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldWorkerServiceImpl implements FieldWorkerService {
    @Autowired
    private FieldWorkerRepo fieldWorkerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FieldWorkerDto createFieldWorker(FieldWorkerDto fieldWorkerDto) {
        FieldWorker fieldWorker = this.modelMapper.map(fieldWorkerDto, FieldWorker.class);
        FieldWorker savedFieldWorker = this.fieldWorkerRepo.save(fieldWorker);
        return this.modelMapper.map(savedFieldWorker, FieldWorkerDto.class);
    }

    @Override
    public FieldWorkerDto updateFieldWorker(FieldWorkerDto fieldWorkerDto, Integer fwId) {
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> {
            return new ResourceNotFoundException("FieldWorker", "fieldWorkerId", fwId);
        });
        fieldWorker.setFname(fieldWorkerDto.getFname());
        fieldWorker.setLname(fieldWorkerDto.getLname());
        fieldWorker.setGender(fieldWorkerDto.getGender());
        fieldWorker.setDOB(fieldWorkerDto.getDOB());
        fieldWorker.setPhoneNo(fieldWorkerDto.getPhoneNo());
        fieldWorker.setAddress(fieldWorkerDto.getAddress());
        fieldWorker.setRegistrationDate(fieldWorkerDto.getRegistrationDate());
        FieldWorker updatedFieldWorker = this.fieldWorkerRepo.save(fieldWorker);
        return this.modelMapper.map(updatedFieldWorker, FieldWorkerDto.class);
    }

    @Override
    public FieldWorkerDto getFieldWorkerById(Integer fwId) {
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorker", "fieldWorkerId", fwId);
        });
        return this.modelMapper.map(fieldWorker, FieldWorkerDto.class);
    }

    @Override
    public List<FieldWorkerDto> getAllFieldWorkers() {
        List<FieldWorker> fieldWorkers = this.fieldWorkerRepo.findAll();
        List<FieldWorkerDto> fieldWorkerDtos = fieldWorkers.stream().map(fieldWorker -> this.modelMapper.map(fieldWorker, FieldWorkerDto.class)).collect(Collectors.toList());
        return fieldWorkerDtos;
    }

    @Override
    public void deleteFieldWorker(Integer fwId) {
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorker", "fieldWorkerId", fwId);
        });
        this.fieldWorkerRepo.delete(fieldWorker);
    }
    @Override
    public String getPhoneNo(Integer fId)
    {
        FieldWorker fieldWorker= this.fieldWorkerRepo.findById(fId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorker", "fieldWorkerId", fId); });
        String phoneNo=fieldWorker.getPhoneNo();
        return phoneNo;
    }

}
