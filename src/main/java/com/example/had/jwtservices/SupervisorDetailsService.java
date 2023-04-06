package com.example.had.jwtservices;

import com.example.had.entities.Supervisor;
import com.example.had.repositories.SupervisorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupervisorDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private SupervisorRepo supervisorRepo;
    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        Supervisor supervisor = supervisorRepo.findSupervisorByPhoneNo(phoneNo);
        System.out.println(supervisor.getPhoneNo());
        if(supervisor==null){
            supervisor = new Supervisor();
            supervisor.setPhoneNo(phoneNo);
            supervisorRepo.save(supervisor);
        }
        return new org.springframework.security.core.userdetails.User(supervisor.getPhoneNo(), "",
                new ArrayList<>());
    }

}
