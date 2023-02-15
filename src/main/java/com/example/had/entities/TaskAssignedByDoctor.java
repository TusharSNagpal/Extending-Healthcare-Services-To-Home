package com.example.had.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="taskAssignedByDoctor")
@Table(name="taskAssignedByDoctor")
public class TaskAssignedByDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @Column
    private String task1;

    @Column
    private String task2;

    @Column
    private String task3;

    @OneToOne
    @JoinColumn(name="folloUpId")
    private FollowUps followUps;
}
