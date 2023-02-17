package com.example.had.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="visits")
@Table(name="visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visitId;

    @Column
    private int isActive;

    @Column
    private String prescription;

    @Column
    private String visitDate;

    @Column
    private String symptoms;


    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name="patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctorInHospital")
    private DoctorInHospital doctorInHospital;
}
