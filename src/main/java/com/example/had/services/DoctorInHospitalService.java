package com.example.had.services;

import com.example.had.payloads.DoctorInHospitalDto;
import com.example.had.payloads.FieldWorkerInHospitalDto;

public interface DoctorInHospitalService {
    DoctorInHospitalDto updateDoctorInHospital(DoctorInHospitalDto doctorInHospitalDto, Integer docInHospID);
    DoctorInHospitalDto getDoctorInHospitalById(Integer docInHospId);

    void registerDoctor(Integer fwId, Integer hosId);
    String getPhoneNo(Integer sId);

}
