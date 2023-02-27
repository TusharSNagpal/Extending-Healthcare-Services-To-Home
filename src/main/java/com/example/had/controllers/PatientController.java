package com.example.had.controllers;

//import com.example.had.payloads.ApiResponse;
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
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto patientDto1 = this.patientService.createPatient(patientDto);
        return new ResponseEntity<>(patientDto1, HttpStatus.CREATED);
    }

    @GetMapping("/search/{patientId}")
    public ResponseEntity<Integer> searchingPatient(@PathVariable int patientId){
        return ResponseEntity.ok(this.patientService.searchPatient(patientId));
    }
}