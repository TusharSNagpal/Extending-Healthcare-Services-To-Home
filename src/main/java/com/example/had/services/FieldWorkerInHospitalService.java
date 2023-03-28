package com.example.had.services;

import com.example.had.entities.FieldWorkerInHospital;
import com.example.had.payloads.DoctorDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;

import java.util.List;

public interface FieldWorkerInHospitalService {

    FieldWorkerInHospitalDto updateFieldWorkerInHospital(FieldWorkerInHospitalDto fieldWorkerInHospitalDto, Integer fwInHospID);
    FieldWorkerInHospitalDto geFieldWorkerInHospitalById(Integer fwInHospId);

    void registerFieldWorker(Integer fwId, Integer hosId);
    List<FieldWorkerInHospitalDto> getFieldWorker(Integer hospitalId);
    String getPhoneNo(Integer sId);


}
