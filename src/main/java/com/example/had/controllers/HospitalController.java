package com.example.had.controllers;

import com.example.had.payloads.HospitalDto;
import com.example.had.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/")
    public ResponseEntity<HospitalDto> createHospital(@RequestBody HospitalDto hospitalDto) {
        HospitalDto createHospitalDto = this.hospitalService.createHospital(hospitalDto);
        return new ResponseEntity<>(createHospitalDto, HttpStatus.CREATED);
    }

    @PutMapping("/{hospitalId}")
    public ResponseEntity<HospitalDto> updateHospital(@RequestBody HospitalDto hospitalDto, @PathVariable Integer hospitalId) {
        HospitalDto updatedHospital = this.hospitalService.updateHospital(hospitalDto,hospitalId);
        return ResponseEntity.ok(updatedHospital);
    }

    @DeleteMapping("/{hospitalId}")
    public void deleteHospital(@PathVariable Integer hospitalId) {
        this.hospitalService.deleteHospital(hospitalId);
    }
    

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalDto> getHospital(@PathVariable Integer hospitalId) {
        return ResponseEntity.ok(this.hospitalService.getHospitalById(hospitalId));
    }

    @GetMapping("/")
    public ResponseEntity<List<HospitalDto>> getAllHospitals() {
        return ResponseEntity.ok(this.hospitalService.getAllHospitals());
    }

    @GetMapping("/noSupervisor")
    public ResponseEntity<List<HospitalDto>> supervisorNotAssignedHospitals() {
        return ResponseEntity.ok(this.hospitalService.supervisorNotAssignedHospitals());
    }

}
