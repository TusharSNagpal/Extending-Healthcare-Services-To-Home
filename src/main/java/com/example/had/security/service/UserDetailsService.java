package com.example.had.security.service;

import com.example.had.entities.Actors;
import com.example.had.entities.Doctor;
import com.example.had.entities.FieldWorker;
import com.example.had.entities.Supervisor;
import com.example.had.repositories.ActorsRepo;
import com.example.had.repositories.DoctorRepo;
import com.example.had.repositories.FieldWorkerRepo;
import com.example.had.repositories.SupervisorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private ActorsRepo actorsRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private FieldWorkerRepo fieldWorkerRepo;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        Actors actor = actorsRepo.findActorsByPhoneNo(phoneNo);
        String role = actor.getRole();
        if(role.equals("supervisor")) {
            Supervisor supervisor = supervisorRepo.findSupervisorByPhoneNo(phoneNo);
            return new org.springframework.security.core.userdetails.User(supervisor.getPhoneNo(), "",
                    new ArrayList<>());
        }
        else if(role.equals("doctor")) {
            Doctor doctor = doctorRepo.findDoctorByPhoneNo(phoneNo);
            return new org.springframework.security.core.userdetails.User(doctor.getPhoneNo(), "",
                    new ArrayList<>());
        }
        else {
            FieldWorker fieldWorker = fieldWorkerRepo.findFieldWorkerByPhoneNo(phoneNo);
            return new org.springframework.security.core.userdetails.User(fieldWorker.getPhoneNo(), "",
                    new ArrayList<>());
        }
    }
}
