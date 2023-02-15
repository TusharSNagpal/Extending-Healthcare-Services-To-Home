package com.example.had.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
    private String gender;

    @Column
    private String phoneNo;

    @Column
    private Date dob;

    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name="supervisorId")
    private Supervisor supervisor;
}
