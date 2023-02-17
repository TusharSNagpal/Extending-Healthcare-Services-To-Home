package com.example.had.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Reference;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supervisors")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supervisorId;

    private String fname;
    private String lname;
    private String gender;
    private String DOB;
    private String phoneNo;
    private String address;
    private String registrationDate;
    @OneToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospitalId;
}
