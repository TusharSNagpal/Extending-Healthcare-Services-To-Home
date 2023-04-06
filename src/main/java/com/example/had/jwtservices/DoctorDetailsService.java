//package com.example.had.jwtservices;
//
//import com.example.had.entities.DoctorInHospital;
//import com.example.had.entities.Supervisor;
//import com.example.had.repositories.DoctorInHospitalRepo;
//import com.example.had.repositories.SupervisorRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class DoctorDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    @Autowired
//    private DoctorInHospitalRepo doctorInHospitalRepo;
//    @Override
//    public UserDetails loadUserByUsername(String phoneNo) {
//        System.out.println(phoneNo);
//        DoctorInHospital doctorInHospital = doctorInHospitalRepo.findByPhoneNo(phoneNo);
//        System.out.println(doctorInHospital.getDoctor().getPhoneNo());
//        if(doctorInHospital==null){
//            doctorInHospital = new DoctorInHospital();
//            doctorInHospital.getDoctor().setPhoneNo(phoneNo);
//            doctorInHospitalRepo.save(doctorInHospital);
//        }
//        return new org.springframework.security.core.userdetails.User(doctorInHospital.getDoctor().getPhoneNo(), "",
//                new ArrayList<>());
//
//    }
//
//}
