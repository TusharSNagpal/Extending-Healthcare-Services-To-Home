package com.example.had.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name="followUps")
@Table(name="followUps")
public class FollowUps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followUpsId;

    @Column
    private String followUpDate;

//    @Column
//    String taskByDoctor;
//
//    @Column
//    String reviewByFieldWorker;

    @Column
    private boolean urgentFlag;

    @Column
    private boolean activeFlag;

    @Column
    private String imagePath;

    @ManyToOne
    @JoinColumn(name="visitId")
    private Visit visitId;

    @ManyToOne
    @JoinColumn(name="fwInHosp")
    private FieldWorkerInHospital fieldWorkerInHospital;

}
