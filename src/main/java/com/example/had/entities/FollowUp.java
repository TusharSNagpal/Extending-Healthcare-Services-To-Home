package com.example.had.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Integer isActive;

    @Column
    private String taskAssignedByDoctor;

    @Column
    private String reviewByFieldWorker;

    @Column
    private String verificationNumber;

    @ManyToOne
    @JoinColumn(name="visitId")
    private Visit visit;

    @ManyToOne
    @JoinColumn(name="fwInHospId")
    private FieldWorkerInHospital fieldWorkerInHospital;

}
