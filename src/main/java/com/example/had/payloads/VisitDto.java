package com.example.had.payloads;

import com.example.had.entities.DoctorInHospital;
import com.example.had.entities.Hospital;
import com.example.had.entities.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class VisitDto {
    
    private int visitId;

    private boolean activeFlag;

    private String prescription;

    private Date visitDate;

    private String symptoms;

    private Hospital hospital;

    private Patient patient;

    private DoctorInHospital doctorInHospital;
}
