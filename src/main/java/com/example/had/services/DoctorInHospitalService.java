package com.example.had.services;

import com.example.had.payloads.DoctorInHospitalDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;

public interface DoctorInHospitalService {
    DoctorInHospitalDto updateDoctorInHospital(DoctorInHospitalDto doctorInHospitalDto, Integer docInHospId);
    DoctorInHospitalDto getDoctorInHospitalById(Integer docInHospId);

    void registerDoctor(Integer docId, Integer hosId);
    String getPhoneNo(Integer docInHospId);

}