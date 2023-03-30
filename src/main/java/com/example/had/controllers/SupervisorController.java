package com.example.had.controllers;

import com.example.had.entities.FieldWorkerInHospital;
import com.example.had.payloads.*;

import com.example.had.services.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@CrossOrigin(origins = "*")

public class SupervisorController {

    @Autowired
    SupervisorService supervisorService;

    @PostMapping("/")
    public ResponseEntity<SupervisorDto> createSupervisor(@RequestBody SupervisorDto supervisorDto) {
        SupervisorDto createSupervisorDto = this.supervisorService.createSupervisor(supervisorDto);
        return new ResponseEntity<>(createSupervisorDto, HttpStatus.CREATED);
    }




    @PutMapping("/{supervisorId}")
    public ResponseEntity<SupervisorDto> updateSupervisor(@RequestBody SupervisorDto supervisorDto, @PathVariable Integer supervisorId) {
        SupervisorDto updatedSupervisor = this.supervisorService.updateSupervisor(supervisorDto,supervisorId);
        return ResponseEntity.ok(updatedSupervisor);
    }

    @DeleteMapping("/{supervisorId}")
    public void deleteSupervisor(@PathVariable Integer supervisorId) {
        this.supervisorService.deleteSupervisor(supervisorId);
    }

    @GetMapping("/{supervisorId}")
    public ResponseEntity<SupervisorDto> getSupervisorById(@PathVariable Integer supervisorId) {
        return ResponseEntity.ok(this.supervisorService.getSupervisorById(supervisorId));
    }


    @GetMapping("/")
    public ResponseEntity<List<SupervisorDto>> getAllSupervisors() {
        return ResponseEntity.ok(this.supervisorService.getAllSupervisors());
    }

    @GetMapping("/phoneNo/{supervisorId}")
    public ResponseEntity<String> getPhoneNo(@PathVariable Integer supervisorId) {
        return ResponseEntity.ok(this.supervisorService.getPhoneNo(supervisorId));
    }
}
