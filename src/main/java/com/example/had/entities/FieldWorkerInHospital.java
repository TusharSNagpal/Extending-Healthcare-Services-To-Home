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
@Entity(name="fieldWorkerInHospital")
@Table(name="fieldWorkerInHospital")
public class FieldWorkerInHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fwInHospId;


    @ManyToOne
    @JoinColumn(name="hospitalId")
    private Hospital hospital;


    @OneToOne
    @JoinColumn(name="fwId")
    private FieldWorker fieldWorker;

    @Column
    private int numOfTasksPerDay;

    @Column
    private String registrationDate;
}
