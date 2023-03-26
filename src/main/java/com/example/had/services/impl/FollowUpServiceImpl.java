package com.example.had.services.impl;

import com.example.had.entities.*;
import com.example.had.exceptions.ResourceNotFoundException;
import com.example.had.payloads.FollowUpDto;
import com.example.had.payloads.PatientDto;
import com.example.had.payloads.VisitDto;
import com.example.had.repositories.*;
import com.example.had.services.FollowUpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowUpServiceImpl implements FollowUpService {



    @Autowired
    private FollowUpRepo followUpRepo;

    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private FieldWorkerInHospitalRepo fieldWorkerInHospitalRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HospitalRepo hospitalRepo;

    @Autowired
    private DoctorInHospitalRepo doctorInHospitalRepo;


    @Override
    public FollowUpDto createFollowUp(FollowUpDto followUpDto) {
        FollowUp followUp = this.modelMapper.map(followUpDto, FollowUp.class);
        int visitId = followUp.getVisit().getVisitId();
        int fwInHospId = followUp.getFieldWorkerInHospital().getFwInHospId();
        System.out.println(fwInHospId);

        Visit visit = this.visitRepo.findById(visitId).orElseThrow(()->new ResourceNotFoundException("Visit","visit id",visitId));
        FieldWorkerInHospital fieldWorkerInHospital = this.fieldWorkerInHospitalRepo.findById(fwInHospId).orElseThrow(()->new ResourceNotFoundException("FieldWorkerInHospital","fwInHosp id",fwInHospId));

        followUp.setVisit(visit);
        followUp.setFieldWorkerInHospital(fieldWorkerInHospital);

        FollowUp savedFollowUp = this.followUpRepo.save(followUp);

        return this.modelMapper.map(savedFollowUp, FollowUpDto.class);

    }

    @Override
    public List<FollowUpDto> followUpsOfFieldWorker(int fwInHospId) {
        FieldWorkerInHospital fieldWorkerInHospital = this.fieldWorkerInHospitalRepo.findById(fwInHospId).orElseThrow(()->new ResourceNotFoundException("FieldWorkerInHospital","fwInHospId",fwInHospId));
        List<FollowUp> followUps = this.followUpRepo.findAllByIsActiveAndFieldWorkerInHospital(1,fieldWorkerInHospital);
        List<FollowUpDto> followUpDtos = followUps.stream().map((followUp -> this.modelMapper.map(followUp, FollowUpDto.class))).collect(Collectors.toList());
        return followUpDtos;
    }

    @Override
    public void updateFollowUpByFieldWorker(FollowUpDto followUpDto, int followUpId) {
        FollowUp followUp = this.followUpRepo.findById(followUpId).orElseThrow(()-> new ResourceNotFoundException("FollowUp","followUpId",followUpId));
        followUp.setReviewByFieldWorker(followUpDto.getReviewByFieldWorker());
        followUp.setIsActive(2);
        followUp.setUrgentFlag(false);
        this.followUpRepo.save(followUp);
    }
//
    @Override
    public void updateFollowUpByDoctor(int followUpId) {
        FollowUp followUp = this.followUpRepo.findById(followUpId).orElseThrow(()-> new ResourceNotFoundException("FollowUp","followUpId",followUpId));
        followUp.setIsActive(0);
        this.followUpRepo.save(followUp);
    }

    @Override
    public List<FollowUpDto> oldFollowUps(int visitId) {
        Visit visit = this.visitRepo.findById(visitId).orElseThrow(()->new ResourceNotFoundException("Visit","visit id",visitId));
        List<FollowUp> followUps = this.followUpRepo.findAllByIsActiveAndVisit(0,visit);
        List<FollowUpDto> followUpDtos = followUps.stream().map((followUp -> this.modelMapper.map(followUp, FollowUpDto.class))).collect(Collectors.toList());
        return followUpDtos;

    }

    @Override
    public List<FollowUpDto> followUpsOfDoctor(int docInHospId) {
        List<FollowUp> followUps = this.followUpRepo.findAllByVisits(docInHospId);
        List<FollowUpDto> followUpDtos = followUps.stream().map((followUp -> this.modelMapper.map(followUp, FollowUpDto.class))).collect(Collectors.toList());
        return followUpDtos;

    }

    @Override
    public List<FollowUpDto> remainingFollowUps(int hospitalId) {
        List<FollowUp> followUps = this.followUpRepo.findRemainingFollowUps(hospitalId);
        List<FollowUpDto> followUpDtos = followUps.stream().map((followUp -> this.modelMapper.map(followUp, FollowUpDto.class))).collect(Collectors.toList());
        return followUpDtos;

    }

    @Override
    public void assignFieldWorkerToFollowUp(int followUpId, int fwInHospId) {
        FollowUp followUp = this.followUpRepo.findById(followUpId).orElseThrow(()-> new ResourceNotFoundException("FollowUp","followUpId",followUpId));
        FieldWorkerInHospital fieldWorkerInHospital = this.fieldWorkerInHospitalRepo.findById(fwInHospId).orElseThrow(()->new ResourceNotFoundException("FieldWorkerInHospital","fwInHospId",fwInHospId));
        followUp.setFieldWorkerInHospital(fieldWorkerInHospital);
        this.followUpRepo.save(followUp);
    }
}
