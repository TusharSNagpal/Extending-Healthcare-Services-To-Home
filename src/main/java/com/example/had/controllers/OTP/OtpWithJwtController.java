package com.example.had.controllers.OTP;

//import com.example.had.jwtservices.UserDetailsService;
import com.example.had.entities.DoctorInHospital;
//import com.example.had.jwtservices.DoctorDetailsService;
import com.example.had.jwtservices.SupervisorDetailsService;
import com.example.had.payloads.AuthRequestDto;
import com.example.had.jwtservices.OtpService;
import com.example.had.services.DoctorInHospitalService;
import com.example.had.services.FieldWorkerInHospitalService;
import com.example.had.services.SupervisorService;
import com.example.had.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/client/auth/")
public class OtpWithJwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private SupervisorDetailsService supervisorDetailsService;

    @Autowired
    private FieldWorkerInHospitalService fieldWorkerInHospitalService;

//    @Autowired
//    private DoctorDetailsService doctorDetailsService;


//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private OtpService otpService;



//    @RequestMapping({ "hello" })
//    public String firstPage() {
//        return "Hello World";
//    }

    @RequestMapping(value = "requestOtp/{phoneNo}",method = RequestMethod.GET)
    public Map<String,Object> getOtp(@PathVariable String phoneNo){
        Map<String,Object> returnMap=new HashMap<>();
        try{
            //generate OTP
            String otp = otpService.generateOtp(phoneNo);
//            String otp = "123456";
            returnMap.put("otp", otp);
            returnMap.put("status","success");
            returnMap.put("message","Otp sent successfully");
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.getMessage());
        }

        return returnMap;
    }

    @RequestMapping(value = "verifyOtp/{role}",method = RequestMethod.POST)
    public Map<String,Object> verifyOtp(@RequestBody AuthRequestDto authenticationRequest, @PathVariable String role){
//        System.out.println(role);
        Map<String,Object> returnMap=new HashMap<>();
        try{
            //verify otp
            System.out.println(otpService.getCacheOtp(authenticationRequest.getPhoneNo()));
            System.out.println(authenticationRequest.getOtp());
            if(authenticationRequest.getOtp().equals(otpService.getCacheOtp(authenticationRequest.getPhoneNo()))){
//                System.out.println("here");
                String jwtToken = createAuthenticationToken(authenticationRequest,role);
                returnMap.put("status","success");
                returnMap.put("message","Otp verified successfully");
                returnMap.put("jwt",jwtToken);
                otpService.clearOtp(authenticationRequest.getPhoneNo());
            }else{
                returnMap.put("status","success");
                returnMap.put("message","Otp is either expired or incorrect");
            }

        } catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.getMessage());
        }

        return returnMap;
    }

    //create auth token
    public String createAuthenticationToken(AuthRequestDto authenticationRequest ,String role) throws Exception {
        try {
//            System.out.println(authenticationRequest.getPhoneNo());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getPhoneNo(), "")
            );
//                        System.out.println("here");

        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDetails userDetails = null;
        if(role.equals("supervisor")){
            System.out.println("in supervisor controller");
            userDetails = supervisorDetailsService.loadUserByUsername(authenticationRequest.getPhoneNo());
        }
//        if(role.equals("fieldworker")){
//            System.out.println("in fieldworker controller");
//            userDetails = fieldWorkerInHospitalService.loadUserByUsername(authenticationRequest.getPhoneNo());
//        }
//        if(role.equals("doctor")){
//            System.out.println("in doctor controller");
////            userDetails = supervisorDetailsService.loadUserByUsername(authenticationRequest.getPhoneNo());
//            userDetails = doctorDetailsService.loadUserByUsername(authenticationRequest.getPhoneNo());
//        }
//        if(role.equals("admin")){
//            // implementation incoming
//        }


        return jwtTokenUtil.generateToken(userDetails);
    }
}

