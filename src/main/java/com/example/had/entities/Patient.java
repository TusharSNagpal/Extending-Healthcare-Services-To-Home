package com.example.had.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="patient")
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @Column
    private String fname;

    @Column
    private String lname;

    @Column
    private String address;

    @Column
    private Integer pinCode;

    @Column
    private String gender;

    @Column
    private String phoneNo;

    @Column
    private String dob;

    @Column
    private String registrationDate;

    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name="supervisorId")
    private Supervisor supervisor;
}
