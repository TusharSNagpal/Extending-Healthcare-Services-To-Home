package com.example.had.controllers;

import com.example.had.payloads.ApiResponse;
import com.example.had.payloads.PatientDto;
import com.example.had.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    //PATIENT REGISTER:
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createPatient(@RequestBody PatientDto patientDto){
        this.patientService.createPatient(patientDto);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Patient created successfully", true), HttpStatus.OK);
    }

    @GetMapping("/phoneNo/{patientId}")
    public ResponseEntity<String> getPhoneNo(@PathVariable Integer patientId) {
        return ResponseEntity.ok(this.patientService.getPhoneNo(patientId));
    }
}
