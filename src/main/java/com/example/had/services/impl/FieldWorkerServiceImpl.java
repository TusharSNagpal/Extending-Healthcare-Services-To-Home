package com.example.had.services.impl;

import com.example.had.entities.FieldWorker;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.FieldWorkerDto;
import com.example.had.repositories.FieldWorkerRepo;
import com.example.had.services.FieldWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldWorkerServiceImpl implements FieldWorkerService {
    @Autowired
    FieldWorkerRepo fieldWorkerRepo;
    @Override
    public FieldWorkerDto createFieldWorker(FieldWorkerDto fieldWorkerDto) {
        FieldWorker fieldWorker = this.dtoToFieldWorker(fieldWorkerDto);
        FieldWorker savedFieldWorker = this.fieldWorkerRepo.save(fieldWorker);
        return this.fieldWorkerToDto(savedFieldWorker);
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
        FieldWorker updatedFieldWorker = this.fieldWorkerRepo.save(fieldWorker);
        return this.fieldWorkerToDto(updatedFieldWorker);
    }

    @Override
    public FieldWorkerDto getFieldWorkerById(Integer fwId) {
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorker", "fieldWorkerId", fwId);
        });
        return this.fieldWorkerToDto(fieldWorker);
    }

    @Override
    public List<FieldWorkerDto> getAllFieldWorkers() {
        List<FieldWorker> fieldWorkers = this.fieldWorkerRepo.findAll();
        List<FieldWorkerDto> fieldWorkerDtos = fieldWorkers.stream().map(fieldWorker -> this.fieldWorkerToDto(fieldWorker)).collect(Collectors.toList());
        return fieldWorkerDtos;
    }

    @Override
    public void deleteFieldWorker(Integer fwId) {
        FieldWorker fieldWorker = this.fieldWorkerRepo.findById(fwId).orElseThrow(() -> {
            return new ResourceNotFoundException("fieldWorker", "fieldWorkerId", fwId);
        });
        this.fieldWorkerRepo.delete(fieldWorker);
    }

    public FieldWorker dtoToFieldWorker(FieldWorkerDto fieldWorkerDto) {
        FieldWorker fieldWorker = new FieldWorker();
        fieldWorker.setFwId(fieldWorkerDto.getFwId());
        fieldWorker.setFname(fieldWorkerDto.getFname());
        fieldWorker.setLname(fieldWorkerDto.getLname());
        fieldWorker.setGender(fieldWorkerDto.getGender());
        fieldWorker.setDOB(fieldWorkerDto.getDOB());
        fieldWorker.setPhoneNo(fieldWorkerDto.getPhoneNo());
        fieldWorker.setAddress(fieldWorkerDto.getAddress());
        return fieldWorker;
    }

    public FieldWorkerDto fieldWorkerToDto(FieldWorker fieldWorker) {
        FieldWorkerDto fieldWorkerDto = new FieldWorkerDto();
        fieldWorkerDto.setFwId(fieldWorker.getFwId());
        fieldWorkerDto.setFname(fieldWorker.getFname());
        fieldWorkerDto.setLname(fieldWorker.getLname());
        fieldWorkerDto.setGender(fieldWorker.getGender());
        fieldWorkerDto.setDOB(fieldWorker.getDOB());
        fieldWorkerDto.setPhoneNo(fieldWorker.getPhoneNo());
        fieldWorkerDto.setAddress(fieldWorker.getAddress());
        return fieldWorkerDto;
    }
}
