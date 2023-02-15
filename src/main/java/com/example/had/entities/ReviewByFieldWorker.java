package com.example.had.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="reviewByFieldWorker")
@Table(name="reviewByFieldWorker")
public class ReviewByFieldWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column
    private String review1;

    @Column
    private String review2;

    @Column
    private String review3;

    @OneToOne
    @JoinColumn(name="followUpId")
    private FollowUps followUps;
}
