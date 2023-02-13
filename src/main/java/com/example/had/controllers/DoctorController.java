package com.example.had.controllers;

import com.example.had.payloads.DoctorDto;
import com.example.had.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto) {
        DoctorDto createDoctorDto = this.doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(createDoctorDto, HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto, @PathVariable Integer doctorId) {
        DoctorDto updatedDoctor = this.doctorService.updateDoctor(doctorDto,doctorId);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable Integer doctorId) {
        this.doctorService.deleteDoctor(doctorId);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(this.doctorService.getDoctorById(doctorId));
    }

    @GetMapping("/")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(this.doctorService.getAllDoctors());
    }
}
