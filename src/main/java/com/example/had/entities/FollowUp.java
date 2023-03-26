package com.example.had.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="followUp")
@Table(name="followUp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followUpsId;

    @Column
    private String followUpDate;

    @Column
    private Boolean urgentFlag;

    @Column
    private Integer isActive;

    @Column
    private String taskAssignedByDoctor;

    @Column
    private String reviewByFieldWorker;

    @ManyToOne
    @JoinColumn(name="visitId")
    private Visit visit;

    @ManyToOne
    @JoinColumn(name="fwInHospId")
    private FieldWorkerInHospital fieldWorkerInHospital;

}
